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
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.FragmentMainScreenBinding
import com.example.feature_main_screen.presentation.adapter.MainScreenNewMoviesAdapter
import com.example.feature_main_screen.presentation.adapter.MainScreenTrendingMoviesAdapter
import com.example.feature_main_screen.presentation.utils.Data.categories
import com.example.feature_main_screen.presentation.view_model.MainScreenViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>() {

    private lateinit var newMoviesAdapter: MainScreenNewMoviesAdapter
    private lateinit var trendingMoviesAdapter: MainScreenTrendingMoviesAdapter

    private val viewModel by viewModel<MainScreenViewModel>()

    private val glide by inject<RequestManager>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newMoviesAdapter = MainScreenNewMoviesAdapter(glide) {
            findNavController().navigate(Uri.parse("myApp://featureDetailsScreen"))
        }
        trendingMoviesAdapter = MainScreenTrendingMoviesAdapter(glide) {
            findNavController().navigate(Uri.parse("myApp://featureDetailsScreen"))
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is MainScreenEvent.Success -> {
                        event.data.let { data ->
                            newMoviesAdapter.submitList(data)
                            trendingMoviesAdapter.submitList(data)
                        }
                    }
                    else -> Unit
                }
            }
        }

        binding.rvNewMovies.adapter = newMoviesAdapter
        binding.vpTrending.adapter = trendingMoviesAdapter
        binding.btnSeeAllNewMovies.setOnClickListener {
            findNavController().navigate(Uri.parse("myApp://featureDetailsScreen"))
        }

        setupTabLayout()
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

