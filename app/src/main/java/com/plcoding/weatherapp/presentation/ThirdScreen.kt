package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.plcoding.weatherapp.presentation.ui.theme.PrimaryBlueDark

@Composable
fun ThirdScreen(
    modifier: Modifier = Modifier.background(PrimaryBlueDark)
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Screen 3",
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}