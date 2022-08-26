package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.plcoding.weatherapp.presentation.ui.theme.PrimaryBlueDark

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(PrimaryBlueDark),
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Screen 2",
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}