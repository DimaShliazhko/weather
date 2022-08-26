package com.plcoding.weatherapp.presentation.ui.weather

import com.plcoding.weatherapp.data.room.MapsPoint
import com.plcoding.weatherapp.domain.location.LocationTracker
import com.plcoding.weatherapp.domain.repository.DataStoreRepository
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Results
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val locationTracker: LocationTracker,
) {
    suspend operator fun invoke(): Results<WeatherInfo> {
        locationTracker.getCurrentLocation()?.let { location ->
            when (val result =
                repository.getWeatherDate(location.latitude, location.longitude)) {
                is Results.Success -> {
                    dataStoreRepository.saveMapPoint(MapsPoint(lng = location.longitude, lat = location.latitude))
                    return Results.Success(result.data)
                }
                is Results.Error -> {
                    return Results.Error(result.message ?: "")
                }
            }

        } ?: kotlin.run {
            val savedLastPoint = dataStoreRepository.readMapPoint()
            if (savedLastPoint != null) {
                val result = repository.getWeatherDate(savedLastPoint.lat, savedLastPoint.lng)
                return Results.Success(result.data)
            }
            return Results.Error("Add permission")
        }
    }
}
