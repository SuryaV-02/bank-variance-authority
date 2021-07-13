package com.sparrow.bankvarianceauthority

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val btn_initiateTransfer = findViewById<Button>(R.id.btn_initiateTransfer)
        btn_initiateTransfer.setOnClickListener {
            val intent = Intent(this,MainScreen::class.java)
            startActivity(intent)
        }
        val btn_checkHistory = findViewById<Button>(R.id.btn_checkHistory)
        btn_checkHistory.setOnClickListener {
            val intent = Intent(this,HistoryScreen::class.java)
            startActivity(intent)
        }
    }
}