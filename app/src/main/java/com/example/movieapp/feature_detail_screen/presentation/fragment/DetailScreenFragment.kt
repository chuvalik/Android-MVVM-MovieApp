package com.example.movieapp.feature_detail_screen.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.example.movieapp.core.ui.BaseFragment
import com.example.movieapp.databinding.FragmentDetailScreenBinding
import com.example.movieapp.feature_detail_screen.domain.model.DetailMovieDomain
import com.example.movieapp.feature_detail_screen.presentation.adapter.DetailScreenCastAdapter
import com.example.movieapp.feature_detail_screen.presentation.adapter.DetailScreenRecommendationAdapter
import com.example.movieapp.feature_detail_screen.presentation.view_model.DetailScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class DetailScreenFragment : BaseFragment<FragmentDetailScreenBinding>() {

    @Inject
    lateinit var glide: RequestManager

    private val viewModel: DetailScreenViewModel by viewModels()

    private lateinit var castAdapter: DetailScreenCastAdapter
    private lateinit var recommendationAdapter: DetailScreenRecommendationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        castAdapter = DetailScreenCastAdapter(glide)
        recommendationAdapter = DetailScreenRecommendationAdapter(glide)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is DetailScreenEvent.Success -> {
                        bindData(detailMovieDomain = event.data)
                    }
                    is DetailScreenEvent.Loading -> {

                    }
                    is DetailScreenEvent.Failure -> {

                    }
                }
            }
        }
    }

    private fun bindData(detailMovieDomain: DetailMovieDomain) {
        binding.tvTitle.text = detailMovieDomain.title
        glide.load(detailMovieDomain.poster).into(binding.ivPoster)
        glide.load(detailMovieDomain.poster).into(binding.ivBackground)
        binding.tvDuration.text = "120 min"
        binding.tvGenre.text = detailMovieDomain.genre
        binding.tvMovieRating.text = detailMovieDomain.rating
        binding.tvOverview.text = detailMovieDomain.overview

        castAdapter.submitList(detailMovieDomain.cast)
        binding.rvCast.adapter = castAdapter
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailScreenBinding.inflate(inflater, container, false)
}