package com.example.feature_main_screen.presentation.fragment

import android.net.Uri
import android.os.Bundle
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
import com.example.feature_main_screen.domain.model.NewMovieDomain
import com.example.feature_main_screen.presentation.adapter.NewMoviesAdapter
import com.example.feature_main_screen.presentation.adapter.TrendingMoviesAdapter
import com.example.feature_main_screen.presentation.utils.Data.categories
import com.example.feature_main_screen.presentation.view_model.MainScreenViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>() {

    private lateinit var trendingMoviesAdapter: TrendingMoviesAdapter
    private lateinit var newMoviesAdapter: NewMoviesAdapter

    private val viewModel by viewModel<MainScreenViewModel>()

    private val glide by inject<RequestManager>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabLayout()

        newMoviesAdapter = NewMoviesAdapter(glide)
        trendingMoviesAdapter = TrendingMoviesAdapter(glide) {
            findNavController().navigate(Uri.parse(Constants.DETAILS_SCREEN_DEEP_LINK))
        }
        binding.rvNewMovies.adapter = newMoviesAdapter
        binding.vpTrending.adapter = trendingMoviesAdapter


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is MainScreenEvent.Success -> bindData(event.data)
                    else -> Unit
                }
            }
        }

        binding.btnSeeAllNewMovies.setOnClickListener {
            findNavController().navigate(Uri.parse(Constants.DETAILS_SCREEN_DEEP_LINK))
        }

    }

    private fun bindData(data: List<NewMovieDomain>) {
        newMoviesAdapter.items = data
        trendingMoviesAdapter.items = data
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

