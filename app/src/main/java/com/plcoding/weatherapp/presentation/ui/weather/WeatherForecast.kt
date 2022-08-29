package com.plcoding.weatherapp.presentation.ui.weather

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.presentation.HourWeatherDisplay
import java.time.format.DateTimeFormatter
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier,
    graphColor: Color = Color.Blue
) {
    val spacing = 100f
    val density = LocalDensity.current
    val scrollState = rememberScrollState()
    val transparentGraphColor = remember {
        graphColor.copy(alpha = 0.5f)
    }

    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    state.weatherInfo?.weaterDataPerDay?.get(0)?.let { data ->

        val upperValue = remember {
            state.weatherInfo.weaterDataPerDay[0]
                ?.maxOfOrNull { it.temperatureCelsius }?.plus(1)?.roundToInt() ?: 0
        }

        val lowerValue = remember {
            state.weatherInfo.weaterDataPerDay[0]
                ?.minOfOrNull { it.temperatureCelsius }?.plus(-1)?.roundToInt() ?: 0
        }


        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Canvas(
                modifier = modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
                    .height(200.dp),
            ) {
                val spacePerHour = (size.width - spacing) / data.size
                (0 until data.size - 1 step 4).forEach { i ->
                    val info = data[i]
                    val hour = info.time.format(DateTimeFormatter.ofPattern("HH:mm"))
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            hour,
                            spacing + i * spacePerHour,
                            size.height - 5,
                            textPaint
                        )
                    }
                }

                val tempStep = (upperValue - lowerValue) / 5f
                (1..5).forEach { i ->
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            round(lowerValue + tempStep * i).toString(),
                            30f,
                            size.height - spacing - i * size.height / 5f,
                            textPaint
                        )
                    }
                }

                var lastX = 0f
                val strokePath = Path().apply {
                    val height = size.height
                    for (i in data.indices) {
                        val info = data[i]
                        val nextInfo = data.getOrNull(i + 1) ?: data.last()
                        val leftRation = (info.temperatureCelsius - lowerValue) / (upperValue - lowerValue)
                        val rightRation = (nextInfo.temperatureCelsius - lowerValue) / (upperValue - lowerValue)


                        val x1 = spacing + i * spacePerHour
                        val y1 = height - spacing - (leftRation * height).toFloat()

                        val x2 = spacing + (i + 1) * spacePerHour
                        val y2 = height - spacing - (rightRation * height).toFloat()

                        if (i == 0) {
                            moveTo(x1, y1)
                        }
                        lastX = (x1 + x2) / 2f
                        quadraticBezierTo(
                            x1, y1, lastX, (y1 + y2) / 2f
                        )
                    }
                }

                val fillPath = android.graphics.Path(strokePath.asAndroidPath())
                    .asComposePath()
                    .apply {
                        lineTo(lastX, size.height - spacing)
                        lineTo(spacing, size.height - spacing)
                        close()
                    }

                drawPath(
                    path = fillPath,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            transparentGraphColor,
                            Color.Transparent,
                        ),
                        endY = size.height - spacing
                    )
                )

                drawPath(
                    path = strokePath,
                    color = graphColor,
                    style = Stroke(
                        width = 3.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(content = {
                items(data) { wetherDate ->
                    HourWeatherDisplay(
                        weatherData = wetherDate,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
            })
        }
    }
}