package com.sparrow.bankvarianceauthority

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
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
        checkAndAddUsersToDB()
        getUsersFromDatabase()
        displayUsers()
    }

    private fun getUsersFromDatabase() {
        val dbHelper = SqliteOpenHelper(this,null)
        customerList = dbHelper.getCustomerData()
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