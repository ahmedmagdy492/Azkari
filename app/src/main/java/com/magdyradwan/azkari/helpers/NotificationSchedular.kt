package com.magdyradwan.azkari.helpers

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import com.magdyradwan.azkari.AzkarActivity
import com.magdyradwan.azkari.R

class NotificationSchedular(var context: Context) {

    fun createAlarm(time :Long, title: String, desc: String) {
        val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(context, SchedualedBroadcastReceiver::class.java)

        alarmIntent.putExtra("title", title)
        alarmIntent.putExtra("desp", desc)

        val pendingIntent = PendingIntent.getBroadcast(context, 12, alarmIntent, PendingIntent.FLAG_IMMUTABLE)

        if(checkAlarmsAccess()) {
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                time,
                1000 * 60 * 60 * 24,
                pendingIntent
            )
        }
        else {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                time,
                1000 * 60 * 60 * 24,
                pendingIntent
            )
        }
    }


    fun checkAlarmsAccess() : Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S){
            return true
        }
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager?
        return alarmManager?.canScheduleExactAlarms() == true
    }

}