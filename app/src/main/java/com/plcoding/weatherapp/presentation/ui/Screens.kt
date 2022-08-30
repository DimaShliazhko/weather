package com.plcoding.weatherapp.presentation.ui

sealed class NavigationScreens(val route: String) {
    object MainScreen : NavigationScreens("main_screen")
    object NotificationScreen : NavigationScreens("map_screen")
    object SettingScreen : NavigationScreens("setting_screen")
    object DetailScreen : NavigationScreens("detail_screen")

    fun withArgs(vararg args: String): String {
        return  buildString {
            append(route)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}