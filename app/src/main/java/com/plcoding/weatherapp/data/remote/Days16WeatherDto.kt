package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

data class Days16WeatherDto(
    @field:Json(name = "data")
    val data: List<Weather16DaysDto>
)