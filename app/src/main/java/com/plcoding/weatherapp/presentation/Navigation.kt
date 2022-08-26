package com.plcoding.weatherapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.plcoding.weatherapp.presentation.ui.NavigationScreens
import com.plcoding.weatherapp.presentation.ui.map.MapScreen
import com.plcoding.weatherapp.presentation.ui.weather.WeatherScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, NavigationScreens.MainScreen.route) {
        composable(NavigationScreens.MainScreen.route) {
            WeatherScreen()
        }
        composable(NavigationScreens.NotificationScreen.route) {
            MapScreen()
        }
        composable(NavigationScreens.SettingScreen.route) {
            ThirdScreen()
        }
    }
}

