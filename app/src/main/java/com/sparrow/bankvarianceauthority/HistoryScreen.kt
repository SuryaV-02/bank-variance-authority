package com.sparrow.bankvarianceauthority

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class HistoryScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_screen)
        setUpHistoryView()

        val btn_back =findViewById<ImageView>(R.id.btn_back)
        btn_back.setOnClickListener {
            onBackPressed()
        }
    }
    private fun setUpHistoryView() {
        val dbHelper = SqliteOpenHelper(this,null)
        val historyList  = dbHelper.getHistoryData()
        Log.i("SKHST154783@HISTORYACT","${historyList.size}")
        //Toast.makeText(this, "${historyList.size}", Toast.LENGTH_SHORT).show()
        val rv_history_screen = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_history_screen)
        val historyAdapter = historyAdapter(this,historyList)
        rv_history_screen.layoutManager = LinearLayoutManager(this)
        rv_history_screen.adapter = historyAdapter
        dbHelper.close()
    }
}