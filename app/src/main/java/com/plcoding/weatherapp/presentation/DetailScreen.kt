package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailScreen(
    name: String?
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hellow, $name")

    }
}