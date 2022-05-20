package com.example.feature_search_screen.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.BaseFragment
import com.example.feature_search_screen.databinding.FragmentSearchScreenBinding
import com.example.feature_search_screen.presentation.view_model.SearchScreenViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchScreenFragment : BaseFragment<FragmentSearchScreenBinding>() {

    private val viewModel by viewModel<SearchScreenViewModel>()

    private val glide by inject<RequestManager>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchScreenBinding.inflate(inflater, container, false)
}