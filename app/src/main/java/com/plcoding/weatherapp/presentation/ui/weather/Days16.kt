package com.plcoding.weatherapp.presentation.ui.weather

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.plcoding.weatherapp.presentation.DaysWeatherDisplay

@Composable
fun Days16(
    state: Weather16DaysState,
    background: Color,
    modifier: Modifier = Modifier
) {

    state.weatherInfo?.data?.let { data ->
        LazyColumn(content = {
            items(data) { weatherData ->
                DaysWeatherDisplay(
                    weatherData = weatherData
                )
            }
        })

    }
}