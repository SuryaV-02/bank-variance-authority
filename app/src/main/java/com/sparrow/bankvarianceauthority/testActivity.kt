package com.sparrow.bankvarianceauthority

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class testActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        addAndCheckHistoryData()
    }

    private fun addAndCheckHistoryData() {
        val dbHelper = SqliteOpenHelper(this,null)
//        dbHelper.addHistoryData("Vinayagar","Murugar",
//            "26 AUG 2022","$23.8","SUCCESS")
//        dbHelper.addHistoryData("Sri","Temple",
//            "18 MAY 2012","$85.3","FAILED")

        val skhstList  = dbHelper.getHistoryData()
        Log.i("SKHST154783@TESTACT","${skhstList.size}")


    }
}