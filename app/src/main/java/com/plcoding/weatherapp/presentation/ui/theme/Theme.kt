package com.plcoding.weatherapp.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.plcoding.weatherapp.presentation.ui.LocalSpacing
import com.plcoding.weatherapp.presentation.ui.Spacing


private val DarkColorPalette = lightColors(
    primary = PrimaryBlueDark,
    secondary = SecondaryBlueDeepDark
)

@Composable
fun WeatherAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(LocalSpacing provides Spacing()) {
    }
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}