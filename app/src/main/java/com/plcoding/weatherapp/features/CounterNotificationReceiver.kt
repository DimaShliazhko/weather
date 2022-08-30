package com.plcoding.weatherapp.features

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CounterNotificationReceiver() : BroadcastReceiver() {
    @Inject
    lateinit var notificationUtils: NotificationUtils
    override fun onReceive(context: Context?, intent: Intent?) {
        notificationUtils.buildForegroundServiceNotification(++Counter.value)
    }
}