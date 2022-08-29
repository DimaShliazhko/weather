package com.plcoding.weatherapp.presentation.ui.weather

import com.plcoding.weatherapp.data.remote.Days16WeatherDto
import com.plcoding.weatherapp.data.remote.Weather16DaysDto
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.presentation.base.State

data class Weather16DaysState(
    val weatherInfo: Days16WeatherDto? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isHasInternetConnection: Boolean = false
) : State