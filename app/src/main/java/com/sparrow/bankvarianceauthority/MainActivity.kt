package com.sparrow.bankvarianceauthority

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkAndAddUsersToDB()
        val ibtn_home_next = findViewById<Button>(R.id.btn_home_next)
        ibtn_home_next.setOnClickListener {
            val intent = Intent(this,HomeScreen::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        val dbHelper = SqliteOpenHelper(this,null)
        val skhstList  = dbHelper.getHistoryData()
        Log.i("SKHST154783@MAINACT","${skhstList.size}")
    }
    private fun checkAndAddUsersToDB() {
        val dbHelper = SqliteOpenHelper(this,null)

        val list = dbHelper.getCustomerData()
        if(list.size>9){ //means db has 10 users
            return
        }
        var uid: UUID
        var d1a : Drawable?
        var d1b : Drawable?
        var profileImage : Bitmap
        var profileAvatar : Bitmap

        d1a= resources.getDrawable(R.drawable.robert_downey_jr)
        d1b = resources.getDrawable(R.drawable.ava_1)
        profileImage  = DbBitmapUtility.drawableToBitmap(d1a)!!
        profileAvatar = DbBitmapUtility.drawableToBitmap(d1b)!!
        uid = UUID.randomUUID()
        dbHelper.addCustomerData(
            "Robert Downey JR",
            "Actor and producer",
            DbBitmapUtility.getBytes(profileImage),
            DbBitmapUtility.getBytes(profileAvatar),
            "$uid",
            "458769.55",
            "robert.jr.4589@outlook.com",
            "405-987-3288",
            "3923 Meadow Drive, Oklahoma"
        )

        d1a= resources.getDrawable(R.drawable.cathrine_hillard)
        d1b = resources.getDrawable(R.drawable.ava_2)
        profileImage  = DbBitmapUtility.drawableToBitmap(d1a)!!
        profileAvatar = DbBitmapUtility.drawableToBitmap(d1b)!!
        uid = UUID.randomUUID()
        dbHelper.addCustomerData(
            "Cathrine Hillard",
            "Working professional at Capgemini",
            DbBitmapUtility.getBytes(profileImage),
            DbBitmapUtility.getBytes(profileAvatar),
            "$uid",
            "5769.78",
            "cathrine_rd44@gmail.com",
            "405-987-3288",
            "4486 Pinnickinnick Street, New Jersey"
        )

        d1a= resources.getDrawable(R.drawable.david_bose)
        d1b = resources.getDrawable(R.drawable.ava_3)
        profileImage  = DbBitmapUtility.drawableToBitmap(d1a)!!
        profileAvatar = DbBitmapUtility.drawableToBitmap(d1b)!!
        uid = UUID.randomUUID()
        dbHelper.addCustomerData(
            "David Bose",
            "CEO at google",
            DbBitmapUtility.getBytes(profileImage),
            DbBitmapUtility.getBytes(profileAvatar),
            "$uid",
            "477.05",
            "davidbose4877@hotmail.com",
            "885-607-4009",
            "1851 Junkins Avenue, Georgia"
        )

        d1a= resources.getDrawable(R.drawable.emma_watson)
        d1b = resources.getDrawable(R.drawable.ava_4)
        profileImage  = DbBitmapUtility.drawableToBitmap(d1a)!!
        profileAvatar = DbBitmapUtility.drawableToBitmap(d1b)!!
        uid = UUID.randomUUID()
        dbHelper.addCustomerData(
            "Emma watson",
            "Scientist at SpaceX",
            DbBitmapUtility.getBytes(profileImage),
            DbBitmapUtility.getBytes(profileAvatar),
            "$uid",
            "477.05",
            "mail.me.emmawatson85@gmail.com",
            "665-708-0589",
            "15028 Massachusetts Avenue, Washington DC"
        )
        d1a= resources.getDrawable(R.drawable.gal_gadot)
        d1b = resources.getDrawable(R.drawable.ava_5)
        profileImage  = DbBitmapUtility.drawableToBitmap(d1a)!!
        profileAvatar = DbBitmapUtility.drawableToBitmap(d1b)!!
        uid = UUID.randomUUID()
        dbHelper.addCustomerData(
            "Gal Gadot",
            "Actress and fashion designer",
            DbBitmapUtility.getBytes(profileImage),
            DbBitmapUtility.getBytes(profileAvatar),
            "$uid",
            "477.05",
            "gal77@outook.com",
            "458-775-1971",
            "4816 Pine Street, Pennsylvania"
        )

        d1a= resources.getDrawable(R.drawable.james_watt)
        d1b = resources.getDrawable(R.drawable.ava_4)
        profileImage  = DbBitmapUtility.drawableToBitmap(d1a)!!
        profileAvatar = DbBitmapUtility.drawableToBitmap(d1b)!!
        uid = UUID.randomUUID()
        dbHelper.addCustomerData(
            "James Watt",
            "Music Director at Black Bird Studios",
            DbBitmapUtility.getBytes(profileImage),
            DbBitmapUtility.getBytes(profileAvatar),
            "$uid",
            "477.05",
            "jameswatt23@hotmail.com",
            "545-788-9436",
            "869 Shadowmar Drive, New Orleans"
        )

        d1a= resources.getDrawable(R.drawable.jenny_red)
        d1b = resources.getDrawable(R.drawable.ava_2)
        profileImage  = DbBitmapUtility.drawableToBitmap(d1a)!!
        profileAvatar = DbBitmapUtility.drawableToBitmap(d1b)!!
        uid = UUID.randomUUID()
        dbHelper.addCustomerData(
            "Jenny red",
            "Proessional photographer",
            DbBitmapUtility.getBytes(profileImage),
            DbBitmapUtility.getBytes(profileAvatar),
            "$uid",
            "1577.05",
            "redrhidinghood16@outlook.com",
            "889-328-2045",
            "1326 Snowbird Lane, Nebraska"
        )
        d1a= resources.getDrawable(R.drawable.jin_dong)
        d1b = resources.getDrawable(R.drawable.ava_5)
        profileImage  = DbBitmapUtility.drawableToBitmap(d1a)!!
        profileAvatar = DbBitmapUtility.drawableToBitmap(d1b)!!
        uid = UUID.randomUUID()
        dbHelper.addCustomerData(
            "Jin Dong",
            "Creative artist",
            DbBitmapUtility.getBytes(profileImage),
            DbBitmapUtility.getBytes(profileAvatar),
            "$uid",
            "477.05",
            "jindong55@hotmail.com",
            "889-475-4679",
            "2960 Oak Way, Nebraska"
        )
        d1a= resources.getDrawable(R.drawable.matt_willson)
        d1b = resources.getDrawable(R.drawable.ava_9)
        profileImage  = DbBitmapUtility.drawableToBitmap(d1a)!!
        profileAvatar = DbBitmapUtility.drawableToBitmap(d1b)!!
        uid = UUID.randomUUID()
        dbHelper.addCustomerData(
            "Matt Willson",
            "Doctor at Louisville",
            DbBitmapUtility.getBytes(profileImage),
            DbBitmapUtility.getBytes(profileAvatar),
            "$uid",
            "477.05",
            "mattgonnawilson1996@outlook.com",
            "545-788-9436",
            "3724 Broaddus Avenue, Kentucky"
        )
        d1a= resources.getDrawable(R.drawable.olivia_hailey)
        d1b = resources.getDrawable(R.drawable.ava_8)
        profileImage  = DbBitmapUtility.drawableToBitmap(d1a)!!
        profileAvatar = DbBitmapUtility.drawableToBitmap(d1b)!!
        uid = UUID.randomUUID()
        dbHelper.addCustomerData(
            "Olivia Hailey",
            "Founder of Suntech Technologies",
            DbBitmapUtility.getBytes(profileImage),
            DbBitmapUtility.getBytes(profileAvatar),
            "$uid",
            "477.05",
            "oliviahaileysuntech@gmail.com",
            "545-788-9436",
            "517 Roosevelt Wilson Lane, Ohio"
        )
        Log.i("SKHST_DB","Insertion TASK success @checkAndAddUsersToDB()")
    }

}