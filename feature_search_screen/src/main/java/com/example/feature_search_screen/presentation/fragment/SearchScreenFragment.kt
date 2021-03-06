package com.example.feature_search_screen.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.BaseFragment
import com.example.feature_core.ui.onQueryTextChanged
import com.example.feature_core.utils.Constants
import com.example.feature_search_screen.databinding.FragmentSearchScreenBinding
import com.example.feature_search_screen.presentation.adapter.MovieAdapter
import com.example.feature_search_screen.presentation.view_model.SearchScreenViewModel
import com.example.feature_search_screen.presentation.view_model.model.SearchScreenState
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.measureTimeMillis


class SearchScreenFragment : BaseFragment<FragmentSearchScreenBinding>() {

    private val viewModel by viewModel<SearchScreenViewModel>()

    private val glide by inject<RequestManager>()

    private var adapter: MovieAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeUi()

        onSearchMovies()
    }

    private fun onSearchMovies() {
        binding.searchView.apply {
            onQueryTextChanged { query ->
                viewModel.fetchMovies(query)
            }
        }
    }

    private fun setupAdapter() {
        adapter = MovieAdapter(glide) { movie ->
            findNavController().navigate(
                Uri.parse("${Constants.DETAILS_SCREEN_DEEP_LINK}/${movie.id}")
            )
        }
        binding.recyclerView.adapter = adapter
    }

    private fun observeUi() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.searchEvent.collect { event ->
                when (event) {
                    is SearchScreenState.Success -> {
                        adapter?.items = event.data
                        binding.recyclerView.isVisible = true
                        showProgressBar(false)
                    }
                    is SearchScreenState.Loading -> {
                        showProgressBar(true)
                    }
                    is SearchScreenState.Failure -> {
                        showProgressBar(true)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchScreenBinding.inflate(inflater, container, false)
}