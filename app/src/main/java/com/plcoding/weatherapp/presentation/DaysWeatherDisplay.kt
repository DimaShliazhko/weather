package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plcoding.weatherapp.R
import com.plcoding.weatherapp.data.remote.Weather16
import com.plcoding.weatherapp.data.remote.Weather16DaysDto
import java.time.format.DateTimeFormatter

@Composable
fun DaysWeatherDisplay(
    weatherData: Weather16DaysDto,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {

    val formattrdTime = remember(weatherData) {
        weatherData.validDate.format((DateTimeFormatter.ofPattern("HH:mm")))
    }

    Row(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = modifier.weight(0.7f),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = formattrdTime,
                color = Color.LightGray
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = weatherData.weather.description,
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = modifier.weight(0.1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id =Weather16.fromWMO(weatherData.weather.code).iconRes),
                contentDescription = null,
                modifier = Modifier.width(40.dp)
            )
        }
        Column(
            modifier = modifier
                .weight(0.2f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${weatherData.maxTemp} °C",
                color = textColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = "${weatherData.lowTemp} °C",
                color = textColor,
                textAlign = TextAlign.End
            )
        }
    }
}