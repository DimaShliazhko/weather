package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.weatherapp.presentation.ui.NavigationScreens
import com.plcoding.weatherapp.presentation.ui.spacing
import com.plcoding.weatherapp.presentation.ui.theme.PrimaryBlueDark

@Composable
fun FlowScreen(
    modifier: Modifier = Modifier.background(PrimaryBlueDark),
    viewModel: FlowViewModel = hiltViewModel(),
    navController: NavController,
) {

    var text by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                onClick = {
                    viewModel.showNotification()
                }
            ) {
                Text(text = "Show notification")
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            TextField(
                value = text, onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )

            Button(onClick = {
                navController.navigate(NavigationScreens.DetailScreen.withArgs(text))
            }) {
                Text(text = "Open New Activity")

            }
        }
    }
}