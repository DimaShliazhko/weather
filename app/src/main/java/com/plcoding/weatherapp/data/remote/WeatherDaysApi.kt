package com.plcoding.weatherapp.data.remote

import com.plcoding.weatherapp.domain.util.KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDaysApi {

    @GET("v2.0/forecast/daily?")
    suspend fun get16DaysData(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("key") key: String = KEY.API_KEY,
    ): Days16WeatherDto
}