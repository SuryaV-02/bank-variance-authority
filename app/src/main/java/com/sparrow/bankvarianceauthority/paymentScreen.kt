package com.sparrow.bankvarianceauthority

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class paymentScreen : AppCompatActivity() {
    var donor : String? =null
    var recipient : String? =null
    var donorCustomer : Customer? = null
    var recipientCustomer : Customer? = null
    var amount :Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_screen)

        donor = intent.getStringExtra("donor")
        recipient = intent.getStringExtra("recipient")
        confirmDonorAndRecipient()
        val btn_proceed = findViewById<Button>(R.id.btn_proceed)
        btn_proceed.setOnClickListener {
            checkDonorBalance()
        }
        val btn_back = findViewById<ImageView>(R.id.btn_back)
        btn_back.setOnClickListener {
            onBackPressed()
        }

    }

    private fun checkDonorBalance() {
        val edt_amount = findViewById<EditText>(R.id.edt_amount)
        amount = edt_amount.text.toString().toDouble()
        val donorBalance : Double? = donorCustomer?.balance

        if(amount!! > donorBalance!!){
            val insuffientDia = Dialog(this)
            insuffientDia.setCancelable(false)
            insuffientDia.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
            insuffientDia.setContentView(R.layout.dialog_alert_insufficient_balance)
            val btn_ok_dia = insuffientDia.findViewById<Button>(R.id.btn_ok_dia)
            btn_ok_dia.setOnClickListener { 
                insuffientDia.dismiss()
            }
            insuffientDia.show()
        }else{
            val intent = Intent(this,confirmPayment::class.java)
            intent.putExtra("donor",donor)
            intent.putExtra("recipient",recipient)
            intent.putExtra("amount",amount.toString())
            startActivity(intent)
            //Toast.makeText(this, "Payment success!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmDonorAndRecipient() {
        val dbHelper = SqliteOpenHelper(this,null)
        donorCustomer = dbHelper.getUser(donor!!)
        recipientCustomer = dbHelper.getUser(recipient!!)
        Log.i("SKHST_DONOR_RECIPIENT","${donorCustomer!!.userName} to ${recipientCustomer!!.userName}")
    }
}