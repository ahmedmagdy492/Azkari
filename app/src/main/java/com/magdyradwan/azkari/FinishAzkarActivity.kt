package com.magdyradwan.azkari

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FinishAzkarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_azkar)

        val totalCountText = findViewById<TextView>(R.id.total_count)
        val btnBack = findViewById<Button>(R.id.goBack)

        val totalCount: Int? = intent?.getIntExtra("totalCount", 0)

        if(totalCount != null && totalCount != 0) {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putInt("totalCount", totalCount)
            editor.apply()

            totalCountText.text = totalCount.toString()
        }


        if (supportActionBar != null) {
            supportActionBar?.hide();
        }


        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        // do nothing
    }
}