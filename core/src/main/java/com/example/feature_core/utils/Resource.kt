package com.example.feature_core.utils

sealed class Resource<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Loading<T>(): Resource<T>(null)
    class Error<T>(error: String?): Resource<T>(error = error)
}