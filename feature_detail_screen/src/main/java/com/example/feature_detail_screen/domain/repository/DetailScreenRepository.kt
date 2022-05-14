package com.example.feature_detail_screen.domain.repository

import com.example.feature_core.utils.Resource
import com.example.feature_detail_screen.domain.model.DetailMovieDomain

interface DetailScreenRepository {

    suspend fun fetchDetailMovie(): Resource<DetailMovieDomain>
}