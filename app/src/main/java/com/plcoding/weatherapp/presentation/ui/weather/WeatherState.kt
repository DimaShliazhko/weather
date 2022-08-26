package com.plcoding.weatherapp.presentation.ui.weather

import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.presentation.base.State

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isHasInternetConnection: Boolean = false
) : State