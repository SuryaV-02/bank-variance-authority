package com.sparrow.bankvarianceauthority

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class paymentSuccessScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success_screen)
        setPaymentStatus()
    }

    private fun setPaymentStatus() {
        val gif_status = findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gif_status)
        val tv_payment_status = findViewById<TextView>(R.id.tv_payment_status)
        val btn_continue = findViewById<Button>(R.id.btn_continue)

        val statusWord = intent.getBooleanExtra("status",true)
        if(!statusWord){
            //Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show()
            gif_status.setImageResource(R.drawable.blue_cross)
            tv_payment_status.text = "Payment Cancelled"
        }else{
            //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            gif_status.setImageResource(R.drawable.blue_check)
            tv_payment_status.text = "Payment Success"
        }

        btn_continue.setOnClickListener {
            val intent = Intent(this,MainScreen::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }


    }
}