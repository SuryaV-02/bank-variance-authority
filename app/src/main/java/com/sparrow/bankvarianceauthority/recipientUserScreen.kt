package com.sparrow.bankvarianceauthority

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager

class recipientUserScreen : AppCompatActivity() {
    companion object{
        var UUID : String? = null
    }
    var customerList : ArrayList<Customer>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipient_user_screen)

        UUID = intent.getStringExtra("UUID")

        getUsersFromDatabase()
        displayUsers()

        val btn_back = findViewById<ImageView>(R.id.btn_back)
        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getUsersFromDatabase() {
        val dbHelper = SqliteOpenHelper(this,null)
        customerList = dbHelper.getCustomerData(UUID.toString())
    }
    private fun displayUsers() {
        val rv_main_screen = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_main_screen)
        Log.i("SKHST","${customerList?.size}")
        val customerAdapter = recipientUserListAdapter(this, customerList!!,UUID!!)
        val gridLayoutManager = GridLayoutManager(this,2)

        rv_main_screen.setLayoutManager(gridLayoutManager)
        rv_main_screen.adapter = customerAdapter
        customerAdapter.notifyDataSetChanged()
    }
}