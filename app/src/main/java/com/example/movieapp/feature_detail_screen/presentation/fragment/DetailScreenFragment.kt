package com.example.movieapp.feature_detail_screen.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movieapp.core.ui.BaseFragment
import com.example.movieapp.databinding.FragmentDetailScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreenFragment : BaseFragment<FragmentDetailScreenBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailScreenBinding.inflate(inflater, container, false)
}