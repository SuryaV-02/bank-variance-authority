package com.sparrow.bankvarianceauthority

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class confirmPayment : AppCompatActivity() {
    var donor : String? =null
    var recipient : String? =null
    var amount : String? = null
    var donorCustomer : Customer? = null
    var recipientCustomer : Customer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_payment)
        confirmDonorAndRecipient()
        setDonorRecipientTiles()
        val btn_confirm = findViewById<Button>(R.id.btn_confirm)
        btn_confirm.setOnClickListener {
            initiatePayment()
            val intent = Intent(this,paymentSuccessScreen::class.java)
            startActivity(intent)
        }

        val btn_cancel = findViewById<Button>(R.id.btn_cancel)
        btn_cancel.setOnClickListener {
            val intent = Intent(this,paymentSuccessScreen::class.java)
            intent.putExtra("status",false)
            startActivity(intent)
        }

    }

    private fun initiatePayment() {
        val dbHelper = SqliteOpenHelper(this,null)
        val donorBalance_before = donorCustomer?.balance
        val recipientBalance_before = recipientCustomer?.balance

        val donorBalance_after = donorBalance_before!! - amount!!.toDouble()
        val recipientBalance_after = recipientBalance_before!! + amount!!.toDouble()

        dbHelper.setUserBalance(donor!!,donorBalance_after.toString())
        dbHelper.setUserBalance(recipient!!,recipientBalance_after.toString())

        Log.i("SKHST_15973_DB","Transaction completed successfully")
    }

    private fun setDonorRecipientTiles() {

        val tv_amount_confirmPayment = findViewById<TextView>(R.id.tv_amount_confirmPayment)
        tv_amount_confirmPayment.text = "$" + amount

        val inc_from = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.inc_from)
        val inc_to = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.inc_to)

        val iv_profileImg_from = inc_from.findViewById<com.rishabhharit.roundedimageview.RoundedImageView>(R.id.iv_profileImg)
        val iv_profileAva_from = inc_from.findViewById<ImageView>(R.id.iv_profileAva)
        val tv_profileName_from = inc_from.findViewById<TextView>(R.id.tv_profileName)

        val iv_profileImg_to = inc_to.findViewById<com.rishabhharit.roundedimageview.RoundedImageView>(R.id.iv_profileImg)
        val iv_profileAva_to = inc_to.findViewById<ImageView>(R.id.iv_profileAva)
        val tv_profileName_to = inc_to.findViewById<TextView>(R.id.tv_profileName)

        iv_profileImg_from.setImageBitmap(DbBitmapUtility.getImage(donorCustomer?.profileImage))
        iv_profileAva_from.setImageBitmap(DbBitmapUtility.getImage(donorCustomer?.profileAvatar))
        tv_profileName_from.text = donorCustomer?.userName

        iv_profileImg_to.setImageBitmap(DbBitmapUtility.getImage(recipientCustomer?.profileImage))
        iv_profileAva_to.setImageBitmap(DbBitmapUtility.getImage(recipientCustomer?.profileAvatar))
        tv_profileName_to.text = recipientCustomer?.userName

    }

    private fun confirmDonorAndRecipient() {
        val dbHelper = SqliteOpenHelper(this,null)
        donor = intent.getStringExtra("donor")
        recipient = intent.getStringExtra("recipient")
        amount = intent.getStringExtra("amount")
        donorCustomer = dbHelper.getUser(donor!!)
        recipientCustomer = dbHelper.getUser(recipient!!)
        Log.i("SKHST_19785","${donorCustomer!!.userName} to ${recipientCustomer!!.userName}")
    }
}