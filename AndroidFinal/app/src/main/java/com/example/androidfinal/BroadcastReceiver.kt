package com.example.androidfinal
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.example.project.NotificationUtil

class BroadcastReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {

        val enabled : Boolean =
            Settings.Global.getInt(context?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

        if (context != null) {

            val notimportance = NotificationManager.IMPORTANCE_DEFAULT
            val notchannel =
                NotificationChannel(NotificationUtil.CHANNEL_ID, "MINE", notimportance).apply {
                    description = "descriptionText"
                }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notchannel)
            if (enabled)
                NotificationUtil.showSimpleNotification(context, "Airplane Mode ON")
            else
                NotificationUtil.showSimpleNotification(context, "Airplane Mode OFF")
        }

    }

}