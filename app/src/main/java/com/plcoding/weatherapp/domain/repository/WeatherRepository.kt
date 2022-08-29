package com.plcoding.weatherapp.domain.repository

import com.plcoding.weatherapp.data.remote.Days16WeatherDto
import com.plcoding.weatherapp.domain.util.Results
import com.plcoding.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherDate(lat: Double, long: Double): Results<WeatherInfo>

    suspend fun getWeather16Days(lat: Double, long: Double): Results<Days16WeatherDto>
}