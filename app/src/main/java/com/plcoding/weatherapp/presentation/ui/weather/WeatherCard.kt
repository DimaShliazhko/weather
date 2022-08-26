package com.plcoding.weatherapp.presentation.ui.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.R
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    background: Color,
    modifier: Modifier = Modifier
) {

    state.weatherInfo?.currentWeatherData?.let { date ->

        Card(
            backgroundColor = background,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today ${
                        date.time.format(DateTimeFormatter.ofPattern("HH:mm"))
                    }",
                    modifier = modifier.align(Alignment.End),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = date.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${date.temperatureCelsius} °C",
                    fontSize = 50.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${date.weatherType.weatherDesc} ",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDateDisplay(
                        value = date.pressure.roundToInt(), unit = " hpa",
                        icon = ImageVector.vectorResource(
                            id = R.drawable.ic_pressure
                        ),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )

                    WeatherDateDisplay(
                        value = date.humidity.roundToInt(), unit = " %",
                        icon = ImageVector.vectorResource(
                            id = R.drawable.ic_drop
                        ),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )

                    WeatherDateDisplay(
                        value = date.windSpeed.roundToInt(), unit = " km/h",
                        icon = ImageVector.vectorResource(
                            id = R.drawable.ic_wind
                        ),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }
}