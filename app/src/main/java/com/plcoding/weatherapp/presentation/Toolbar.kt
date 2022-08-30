package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.plcoding.weatherapp.presentation.ui.theme.SecondaryBlueDeepDark
import com.plcoding.weatherapp.presentation.ui.weather.WeatherState

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    state: WeatherState,
    onSearch: (String) -> Unit
) {
    state.weatherInfo?.currentWeatherData?.let {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(SecondaryBlueDeepDark),
            verticalArrangement = Arrangement.Center
        ) {
            var text by remember { mutableStateOf("") }
            var hint by remember { mutableStateOf("Search...") }
            var isHintDisplay by remember { mutableStateOf(true) }

            Box(
                modifier = modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = text,
                    onValueChange = { string ->
                        text = string
                        onSearch(string)
                    },
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle(color = Color.Black),
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .padding(10.dp)
                        .onFocusChanged {
                            isHintDisplay = !it.isFocused
                        }

                )
                if (isHintDisplay) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            modifier = modifier.padding(5.dp),
                            text = hint,
                            color = Color.Gray,
                        )
                    }
                }
            }
        }
    }
}

