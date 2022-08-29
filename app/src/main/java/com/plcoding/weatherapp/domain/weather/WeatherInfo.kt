package com.plcoding.weatherapp.domain.weather

data class WeatherInfo(
    val weaterDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)