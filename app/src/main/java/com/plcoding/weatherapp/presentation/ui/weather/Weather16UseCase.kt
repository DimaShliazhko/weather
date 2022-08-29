package com.plcoding.weatherapp.presentation.ui.weather

import com.plcoding.weatherapp.data.remote.Days16WeatherDto
import com.plcoding.weatherapp.data.remote.Weather16DaysDto
import com.plcoding.weatherapp.data.room.MapsPoint
import com.plcoding.weatherapp.domain.location.LocationTracker
import com.plcoding.weatherapp.domain.repository.DataStoreRepository
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Results
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class Weather16UseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val locationTracker: LocationTracker,
) {
    suspend operator fun invoke(): Results<Days16WeatherDto> {
        locationTracker.getCurrentLocation()?.let { location ->
            when (val result =
                repository.getWeather16Days(location.latitude, location.longitude)) {
                is Results.Success -> {
                    return Results.Success(result.data)
                }
                is Results.Error -> {
                    return Results.Error(result.message ?: "")
                }
            }

        } ?: kotlin.run {
            val savedLastPoint = dataStoreRepository.readMapPoint()
            if (savedLastPoint != null) {
                val result = repository.getWeather16Days(savedLastPoint.lat, savedLastPoint.lng)
                return Results.Success(result.data)
            }
            return Results.Error("Add permission")
        }
    }
}
