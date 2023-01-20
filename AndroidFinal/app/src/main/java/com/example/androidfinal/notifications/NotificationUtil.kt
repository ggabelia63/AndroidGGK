package com.example.project

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.androidfinal.R

object NotificationUtil {
    const val CHANNEL_ID = "FOR_SELECT"

    fun showSimpleNotification(context: Context, text: String){

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Airplane Status")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification)
    }
}