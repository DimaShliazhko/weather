package com.plcoding.weatherapp.domain.weather

data class Weather16Info(
    val weaterDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)