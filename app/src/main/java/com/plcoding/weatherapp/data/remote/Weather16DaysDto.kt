package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

data class Weather16DaysDto(

    @field:Json(name = "valid_date")
    val validDate: String,

    @field:Json(name = "weather")
    val weather: Weather,

    @field:Json(name = "low_temp")
    val lowTemp: Double,

    @field:Json(name = "max_temp")
    val maxTemp: Double,
    )
