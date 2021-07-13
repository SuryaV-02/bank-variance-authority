package com.sparrow.bankvarianceauthority

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ibtn_home_next = findViewById<ImageButton>(R.id.ibtn_home_next)
        ibtn_home_next.setOnClickListener {
            val intent = Intent(this,MainScreen::class.java)
            startActivity(intent)
        }
    }
}