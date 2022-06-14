package com.example.feature_main_screen.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.BaseFragment
import com.example.feature_core.utils.Constants
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.FragmentMainScreenBinding
import com.example.feature_main_screen.presentation.adapter.ComingSoonMoviesAdapter
import com.example.feature_main_screen.presentation.adapter.TrendingMoviesAdapter
import com.example.feature_main_screen.presentation.utils.Data.categories
import com.example.feature_main_screen.presentation.view_model.MainScreenViewModel
import com.example.feature_main_screen.presentation.view_model.model.ComingSoonMovieEvent
import com.example.feature_main_screen.presentation.view_model.model.TrendingMovieEvent
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>() {

    private var trendingMoviesAdapter: TrendingMoviesAdapter? = null
    private var comingSoonMoviesAdapter: ComingSoonMoviesAdapter? = null

    private val viewModel by viewModel<MainScreenViewModel>()

    private val glide by inject<RequestManager>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabLayout()
        setupAdapters()

        observeTrendingMovies()
        observeComingSoonMovies()

        applyBinding()
    }

    private fun applyBinding() = binding.apply {
        searchView.setOnClickListener {
            findNavController().navigate(Uri.parse(Constants.SEARCH_SCREEN_DEEP_LINK))
        }
    }

    private fun setupAdapters() {
        comingSoonMoviesAdapter = ComingSoonMoviesAdapter(glide)
        trendingMoviesAdapter = TrendingMoviesAdapter(glide) { movie ->
            findNavController().navigate(
                Uri.parse("${Constants.DETAILS_SCREEN_DEEP_LINK}/${movie.id}")
            )
        }

        binding.vpComingSoon.adapter = comingSoonMoviesAdapter
        binding.rvTrending.adapter = trendingMoviesAdapter
    }

    private fun observeTrendingMovies() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiTrendingMovieEvent.collect { event ->
                when (event) {
                    is TrendingMovieEvent.Success -> trendingMoviesAdapter?.items = event.data
                    else -> Unit
                }
            }
        }
    }

    private fun observeComingSoonMovies() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiComingSoonMovieEvent.collect { event ->
                when (event) {
                    is ComingSoonMovieEvent.Success -> {
                        comingSoonMoviesAdapter?.items = event.data
                    }
                    is ComingSoonMovieEvent.Failure -> {
                        Log.d("MainScreenFragmentLog", event.error)
                    }
                    else -> Log.d("MainScreenFragmentLog", "?")
                }
            }
        }
    }

    private fun setupTabLayout() = categories.onEachIndexed { index, (title, image) ->
        binding.tabLayoutCategories.addTab(binding.tabLayoutCategories.newTab().setText(title))
        binding.tabLayoutCategories.getTabAt(index)?.apply {
            setCustomView(R.layout.tab_item)
            customView?.findViewById<ImageView>(R.id.tabIcon)
                ?.setImageResource(image)
            customView?.findViewById<TextView>(R.id.tabText)?.text = title
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainScreenBinding.inflate(inflater, container, false)
}

