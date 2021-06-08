package com.nirbhay.nirbhay_24x7.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.nirbhay.nirbhay_24x7.R
import com.nirbhay.nirbhay_24x7.view.activities.HomeActivity


class MyNotificationManager(private val context: Context) {
    private val channelID = "Shake Detector Channel"
    private val channelName = "Shake Detector Notification"


    fun getNotification(): Notification {
        createNotificationChannel()
        val intent = Intent(context, HomeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 26, intent, 0)
        val notificationBuilder = NotificationCompat.Builder(context, channelID)
        notificationBuilder.apply {
            setContentTitle("Shake Detector Running...")
            setContentText("Emergency? Just shake your phone to send your location")
            setSmallIcon(R.drawable.ic_shake)
            setAutoCancel(false)
            color = Color.RED
            setContentIntent(pendingIntent)
        }
        return notificationBuilder.build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(notificationChannel)
        }
    }
}