package com.plcoding.weatherapp.domain.util

sealed class Results<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Results<T>(data)
    class Error<T>(message: String, data: T? = null) : Results<T>(data, message)
}


sealed class Error<T>(val data: T? = null, val message: String? = null) {
    class NoInternetConnection<T>(data: T?) : Error<T>(data)
}