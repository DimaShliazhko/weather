package com.plcoding.weatherapp.presentation.ui

sealed class NavigationScreens (val route : String){
    object MainScreen : NavigationScreens("main_screen")
    object NotificationScreen : NavigationScreens("map_screen")
    object SettingScreen : NavigationScreens("setting_screen")
}