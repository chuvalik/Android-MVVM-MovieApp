package com.example.movieapp.feature_main_screen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.example.movieapp.core.ui.BaseFragment
import com.example.movieapp.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>() {

    @Inject
    lateinit var glide: RequestManager

    private lateinit var newMoviesAdapter: MainScreenNewMoviesAdapter

    private val viewModel: MainScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newMoviesAdapter = MainScreenNewMoviesAdapter(glide)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is MainScreenEvent.Success -> {
                        event.data.let { data ->
                            newMoviesAdapter.submitList(data)
                        }
                    }
                    is MainScreenEvent.Loading -> {
                        // show skeleton while loading data
                    }
                    is MainScreenEvent.Failure -> {
                        // show snackbar with error and continue to show skeleton
                    }
                    else -> Unit
                }
            }
        }

        binding.rvNewMovies.adapter = newMoviesAdapter

    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainScreenBinding.inflate(inflater, container, false)

}