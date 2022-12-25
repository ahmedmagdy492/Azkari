package com.magdyradwan.azkari

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magdyradwan.azkari.helpers.NotificationSchedular
import java.util.*
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private fun getExactHour(hour: Int, minute: Int): Long {
        val cal: Calendar = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
        return cal.timeInMillis
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar?.hide();
        }

        // create morning alarm
        val schedular =  NotificationSchedular(applicationContext)
        schedular.createAlarm(getExactHour(11, 30), "اذكار الصباح", "اذكار الصباح")

        // create evening alarm
        schedular.createAlarm(getExactHour(17, 30), "اذكار المساء", "اذكار المساء")

        if(supportActionBar != null) {
            supportActionBar?.title = "أذكاري"
        }

        val main_list = findViewById<RecyclerView>(R.id.main_list)
        main_list.adapter = MainListAdapter(listOf("اذكار الصباح", "اذكار المساء"))
        main_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onBackPressed() {
        this.finishAffinity()
    }
}