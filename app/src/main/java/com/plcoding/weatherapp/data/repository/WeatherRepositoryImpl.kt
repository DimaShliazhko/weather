package com.plcoding.weatherapp.data.repository

import com.plcoding.weatherapp.data.mappers.toWeatherInfo
import com.plcoding.weatherapp.data.remote.Days16WeatherDto
import com.plcoding.weatherapp.data.remote.WeatherApi
import com.plcoding.weatherapp.data.remote.WeatherDaysApi
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Results
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val api16Days: WeatherDaysApi,
) : WeatherRepository {
    override suspend fun getWeatherDate(lat: Double, long: Double): Results<WeatherInfo> {

        return try {
            Results.Success(
                data = api.getWeatherData(lat, long).weaterData.toWeatherInfo()
            )

        } catch (e: Exception) {
            Results.Error(e.message ?: "An unknown error")
        }

    }

    override suspend fun getWeather16Days(lat: Double, long: Double): Results<Days16WeatherDto> {
        return try {
            Results.Success(
                data = api16Days.get16DaysData(lat, long)
            )

        } catch (e: Exception) {
            Results.Error(e.message ?: "An unknown error")
        }
    }
}