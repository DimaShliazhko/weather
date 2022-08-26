package com.plcoding.weatherapp.domain.data

sealed class InternetStatus() {
    object Available : InternetStatus()
    object UnAvailable : InternetStatus()
}