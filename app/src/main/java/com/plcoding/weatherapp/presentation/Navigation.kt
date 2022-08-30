package com.plcoding.weatherapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            FlowScreen(navController = navController)
        }

        composable(
            route = NavigationScreens.DetailScreen.route +"/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Dima"
                    nullable = true
                }
            )) { entry ->
            DetailScreen(name = entry.arguments?.getString("name"))
        }
    }
}

