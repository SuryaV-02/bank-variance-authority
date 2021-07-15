package com.sparrow.bankvarianceauthority

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.roundToInt

class userDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        val uid = intent.getStringExtra("uid").toString()
        val dbHandler = SqliteOpenHelper(this,null)
        val currentUser = dbHandler.getUser(uid)

        val tv_userName = findViewById<TextView>(R.id.tv_userName)
        val tv_about = findViewById<TextView>(R.id.tv_about)
        val tv_uid = findViewById<TextView>(R.id.tv_uid)
        val tv_balance = findViewById<TextView>(R.id.tv_balance)
        val tv_email = findViewById<TextView>(R.id.tv_email)
        val tv_mobile = findViewById<TextView>(R.id.tv_mobile)
        val tv_address = findViewById<TextView>(R.id.tv_address)
        val iv_profileImage = findViewById<ImageView>(R.id.iv_profileImage)
        val iv_profileAvatar = findViewById<ImageView>(R.id.iv_profileAvatar)
        val btn_transferAmount = findViewById<Button>(R.id.btn_transferAmount)
        val btn_viewHistory = findViewById<Button>(R.id.btn_viewHistory)
        val btn_back = findViewById<ImageView>(R.id.btn_back)


        tv_userName.text = currentUser.userName
        tv_about.text = currentUser.about
        tv_uid.text = currentUser.UID
        tv_balance.text ="$"+String.format("%.2f", currentUser.balance)
        tv_email.text = currentUser.email
        tv_mobile.text = currentUser.mobile
        tv_address.text = currentUser.address

        iv_profileImage.setImageBitmap(DbBitmapUtility.getImage(currentUser.profileImage))
        iv_profileAvatar.setImageBitmap(DbBitmapUtility.getImage(currentUser.profileAvatar))

        btn_back.setOnClickListener {
            onBackPressed()
        }

        btn_transferAmount.setOnClickListener {
            val intent = Intent(this,recipientUserScreen::class.java)
            intent.putExtra("UUID","${currentUser.UID}")
            startActivity(intent)
        }
        btn_viewHistory.setOnClickListener {
            val intent = Intent(this,individualHistoryScreen::class.java)
            intent.putExtra("fromUser","${currentUser.userName}")
            startActivity(intent)
        }

    }
}