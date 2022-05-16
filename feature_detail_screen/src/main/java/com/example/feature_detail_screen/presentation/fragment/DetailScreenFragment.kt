package com.example.feature_detail_screen.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.BaseFragment
import com.example.feature_detail_screen.databinding.FragmentDetailScreenBinding
import com.example.feature_detail_screen.domain.model.DetailMovieDomain
import com.example.feature_detail_screen.presentation.adapter.DetailScreenAdapter
import com.example.feature_detail_screen.presentation.view_model.DetailScreenViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailScreenFragment : BaseFragment<FragmentDetailScreenBinding>() {

    private val viewModel by viewModel<DetailScreenViewModel>()

    private val glide by inject<RequestManager>()

    private lateinit var castAdapter: DetailScreenAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        castAdapter = DetailScreenAdapter(glide)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is DetailScreenEvent.Success -> bindData(detailMovieDomain = event.data)
                    else -> Unit
                }
            }
        }

        binding.btnGoBack.setOnClickListener {
            findNavController().navigateUp()
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

        binding.rvCast.adapter = castAdapter
        castAdapter.items = detailMovieDomain.cast
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailScreenBinding.inflate(inflater, container, false)
}