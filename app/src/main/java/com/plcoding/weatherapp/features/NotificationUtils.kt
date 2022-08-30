package com.plcoding.weatherapp.features

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.plcoding.weatherapp.R
import com.plcoding.weatherapp.presentation.MainActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationUtils @Inject constructor(
    private val context: Context
) {
    private val notificationManager = NotificationManagerCompat.from(context)

    fun createNotificationChannels() {
        if (supportNotificationChannels()) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Use for count"
                }
            )
        }
    }


    fun buildForegroundServiceNotification(counter: Int) {

        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            checkFlag()
        )
        val incrementIntent =
            PendingIntent.getBroadcast(
                context,
                2,
                Intent(context, CounterNotificationReceiver::class.java),
                checkFlag()
            )

        notificationManager.notify(
            1,
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_share_screen)
                .setContentTitle("Increment counter")
                .setContentText(" the counter $counter")
                // .setStyle(NotificationCompat.MessagingStyle)
                .setContentIntent(activityPendingIntent)
                .addAction(
                    R.drawable.ic_drop,
                    "Increment",
                    incrementIntent
                )
                .build()
        )

    }

    private fun checkFlag(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE
        } else {
            0
        }
    }


    private fun supportNotificationChannels(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    companion object {
        private const val CHANNEL_ID = "NOTIFICATION_CHANNEL_ID"
        private const val CHANNEL_NAME = "COUNT_ID"
    }
}