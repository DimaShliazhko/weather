package com.plcoding.weatherapp.data.mappers

import com.plcoding.weatherapp.data.remote.WeaterDataDto
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private data class IndexexWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeaterDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = this.temperature[index]
        val weatherCode = this.weatherCodes[index]
        val windSpeed = this.windSpeeds[index]
        val pressure = this.pressures[index]
        val humidity = this.humidities[index]
        IndexexWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                weatherType = WeatherType.fromWMO(weatherCode),
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map {
            it.data
        }
    }
}

fun WeaterDataDto.toWeatherInfo(): WeatherInfo {
    val weatheDataMap = this.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatheDataMap[0]?.find {
        val hours = if (now.minute <30) now.hour else now.hour +1
        it.time.hour == hours
    }
    return  WeatherInfo(
        weaterDataPerDay = weatheDataMap,
        currentWeatherData = currentWeatherData
    )
}