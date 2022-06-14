package com.example.feature_detail_screen.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.BaseFragment
import com.example.feature_detail_screen.R
import com.example.feature_detail_screen.databinding.FragmentDetailScreenBinding
import com.example.feature_detail_screen.domain.model.MovieDetailsDomain
import com.example.feature_detail_screen.presentation.adapter.CastAdapter
import com.example.feature_detail_screen.presentation.view_model.DetailScreenViewModel
import com.example.feature_detail_screen.presentation.view_model.model.DetailScreenEvent
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailScreenFragment : BaseFragment<FragmentDetailScreenBinding>() {

    private val viewModel by viewModel<DetailScreenViewModel>()

    private val glide by inject<RequestManager>()

    private var castAdapter: CastAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val argument = arguments?.getString("id")
        viewModel.fetchDetailMovie(argument ?: "")

        setupAdapter()

        observeMovieDetails()

        applyBinding()
    }

    private fun applyBinding() = binding.apply {
        btnGoBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupAdapter() {
        castAdapter = CastAdapter(glide)
        binding.rvCast.adapter = castAdapter
    }

    private fun observeMovieDetails() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is DetailScreenEvent.Success -> bindDataToUi(movieDetailsDomain = event.data)
                    is DetailScreenEvent.Failure -> Log.d("DetailTest", event.error)
                    else -> {}
                }
            }
        }
    }

    private fun bindDataToUi(movieDetailsDomain: MovieDetailsDomain) {
        with(binding) {
            ratingBar.rating = movieDetailsDomain.imDbRating.toFloat() / 2
            tvTitle.text = movieDetailsDomain.fullTitle
            tvDuration.text = getString(R.string.movie_duration, movieDetailsDomain.runtimeMins)
            tvGenre.text = movieDetailsDomain.genres
            tvMovieRating.text = movieDetailsDomain.imDbRating
            tvOverview.text = movieDetailsDomain.plot
        }

        glide.load(movieDetailsDomain.image).into(binding.ivPoster)
        glide.load(movieDetailsDomain.image).into(binding.ivBackground)

        castAdapter?.items = movieDetailsDomain.actorList
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailScreenBinding.inflate(inflater, container, false)
}