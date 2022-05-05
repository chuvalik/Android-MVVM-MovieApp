package com.example.movieapp.feature_detail_screen.domain.repository

import com.example.movieapp.core.utils.Resource
import com.example.movieapp.feature_detail_screen.domain.model.DetailMovieDomain

interface DetailScreenRepository {

    suspend fun fetchDetailMovieFromApi(): Resource<DetailMovieDomain>
}