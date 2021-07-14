package com.sparrow.bankvarianceauthority

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager

class individualHistoryScreen : AppCompatActivity() {
    var fromUser : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_history_screen)
        fromUser = intent.getStringExtra("fromUser")
        setUpHistoryView()
        val btn_back = findViewById<ImageView>(R.id.btn_back)
        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpHistoryView() {
        val tv_noPrevTrans = findViewById<TextView>(R.id.tv_noPrevTrans)
        val rv_history_screen = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_individual_history_screen)
        val dbHelper = SqliteOpenHelper(this,null)
        val historyList  = dbHelper.getIndividualHistoryData(fromUser!!)
        if(historyList.size==0){
            rv_history_screen.visibility = View.INVISIBLE
            tv_noPrevTrans.visibility = View.VISIBLE
        }else{
            rv_history_screen.visibility = View.VISIBLE
            tv_noPrevTrans.visibility = View.INVISIBLE
        }
        Log.i("SKHST154783@HISTORYACT","${historyList.size}")
        //Toast.makeText(this, "${historyList.size}", Toast.LENGTH_SHORT).show()
        val historyAdapter = historyAdapter(this,historyList)
        rv_history_screen.layoutManager = LinearLayoutManager(this)
        rv_history_screen.adapter = historyAdapter
        dbHelper.close()
    }
}