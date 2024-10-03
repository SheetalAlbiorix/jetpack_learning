package com.jetpackcopmosedemo.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.jetpackcopmosedemo.R
import com.jetpackcopmosedemo.presentation.navigation.Routes
import kotlin.random.Random

class NotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    fun showBasicNotification() {
        val notNestedIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(
                Intent(
                    Intent.ACTION_VIEW,
                    "myapp://${Routes.Profile.Details.createRoute("0")}".toUri()
                )
            )
            getPendingIntent(
                1234,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        val notification = NotificationCompat.Builder(context, "water_notification")
            .setContentTitle("Water Reminder")
            .setContentIntent(notNestedIntent)
            .setContentText("Time to drink a glass of water")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }
}