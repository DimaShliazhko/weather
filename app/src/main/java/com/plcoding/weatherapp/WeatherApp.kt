package com.plcoding.weatherapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.plcoding.weatherapp.features.NotificationUtils
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WeatherApp : Application() {


    @Inject
    lateinit var notificationUtils: NotificationUtils
    override fun onCreate() {
        super.onCreate()
        notificationUtils.createNotificationChannels()


/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CANNEL,
                "File download",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }*/
    }

    companion object App {
        val CANNEL = "download_cannal"
    }
}