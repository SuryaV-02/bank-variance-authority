package com.sparrow.bankvarianceauthority

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import java.util.*
import kotlin.collections.ArrayList


class MainScreen : AppCompatActivity() {
    var customerList : ArrayList<Customer>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        //generateDummyList()
        getUsersFromDatabase()
        displayUsers()

        val btn_back = findViewById<ImageView>(R.id.btn_back)
        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getUsersFromDatabase() {
        val dbHelper = SqliteOpenHelper(this,null)
        customerList = dbHelper.getCustomerData()
    }


    private fun displayUsers() {
        val rv_main_screen = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_main_screen)
        Log.i("SKHST","${customerList?.size}")
        val customerAdapter = donorUserListAdapter(this, customerList!!)
        //val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        val gridLayoutManager = GridLayoutManager(this,2)

        rv_main_screen.setLayoutManager(gridLayoutManager)
        //rv_main_screen.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        //rv_main_screen.layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)
        rv_main_screen.adapter = customerAdapter
        customerAdapter.notifyDataSetChanged()
    }
}