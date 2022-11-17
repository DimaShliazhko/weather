package com.plcoding.weatherapp.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.plcoding.weatherapp.presentation.ui.BottomNavItem
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        installSplashScreen().apply {
            //setKeepOnScreenCondition()
        }
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
        )
        setContent {
            val isDarkTheme by remember { mutableStateOf(false) }

            WeatherAppTheme(
                isDarkTheme = isDarkTheme
            ) {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = "main_screen",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Map",
                                    route = "map_screen",
                                    icon = Icons.Default.Notifications,
                                    badgeCount = 10
                                ),
                                BottomNavItem(
                                    name = "Settings",
                                    route = "setting_screen",
                                    icon = Icons.Default.Settings
                                )
                            ), navController = navController, onItemClickListener = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    Navigation(navController)
                }

            }
        }
    }
}
