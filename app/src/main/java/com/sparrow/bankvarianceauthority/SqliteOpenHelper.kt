package com.sparrow.bankvarianceauthority

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
        companion object{
            val DATABASE_NAME = "bva.db"
            val DATABASE_VERSION= 1
            val TABLE_CUSTOMER= "customer"
            val TABLE_HISTORY= "history"

            private val COLUMN_ID = "id_"
            private val COLUMN_USERNAME = "username"
            private val COLUMN_ABOUT = "about"
            private val COLUMN_PROFILEIMAGE = "profileimage"
            private val COLUMN_PROFILEAVATAR = "profileavatar"
            private val COLUMN_UID = "uid"
            private val COLUMN_BALANCE = "balance"
            private val COLUMN_EMAIL = "email"
            private val COLUMN_MOBILE = "mobile"
            private val COLUMN_ADDRESS = "address"

            private val COLUMN_ID_ = "_id"
            private val COLUMN_FROMUSER = "fromuser"
            private val COLUMN_TOUSER = "touser"
            private val COLUMN_DATE = "date"
            private val COLUMN_AMOUNT = "amount"
            private val COLUMN_STATUS = "status"
        }

    override fun onCreate(db: SQLiteDatabase?) {
        val createCustomerTableCommand =( "CREATE TABLE $TABLE_CUSTOMER (" +
                "$COLUMN_ID INTEGER PRIMARY KEY,"+
                "$COLUMN_USERNAME TEXT,"+
                "$COLUMN_ABOUT TEXT,"+
                "$COLUMN_PROFILEIMAGE BLOB," +
                "$COLUMN_PROFILEAVATAR BLOB," +
                "$COLUMN_UID TEXT," +
                "$COLUMN_BALANCE TEXT," +
                "$COLUMN_EMAIL TEXT," +
                "$COLUMN_MOBILE TEXT,"+
                "$COLUMN_ADDRESS TEXT)")
        db?.execSQL(createCustomerTableCommand)

        val createHistoryTableCommand =( "CREATE TABLE $TABLE_HISTORY (" +
                "$COLUMN_ID_ INTEGER PRIMARY KEY,"+
                "$COLUMN_FROMUSER TEXT,"+
                "$COLUMN_TOUSER TEXT,"+
                "$COLUMN_DATE TEXT," +
                "$COLUMN_AMOUNT TEXT," +
                "$COLUMN_STATUS TEXT)")
        db?.execSQL(createHistoryTableCommand)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val upgradeCustomerTableCommand =("DROP TABLE IF EXISTS $TABLE_CUSTOMER")
        val upgradeHistoryTableCommand =("DROP TABLE IF EXISTS $TABLE_HISTORY")
        db?.execSQL(upgradeCustomerTableCommand)
        db?.execSQL(upgradeHistoryTableCommand)

        onCreate(db)
    }

    fun addCustomerData(username : String?,
                        about : String?,
                        profileImage : ByteArray?,
                        profileAvatar : ByteArray?,
                        uid:String?,
                        balance :String?,
                        email :String?,
                        mobile : String?,
                        address:String?){

        val values = ContentValues()
        val db = this.writableDatabase

        values.put(COLUMN_USERNAME,username)
        values.put(COLUMN_ABOUT,about)
        values.put(COLUMN_PROFILEIMAGE,profileImage)
        values.put(COLUMN_PROFILEAVATAR,profileAvatar)
        values.put(COLUMN_UID,uid)
        values.put(COLUMN_BALANCE,balance)
        values.put(COLUMN_EMAIL,email)
        values.put(COLUMN_MOBILE,mobile)
        values.put(COLUMN_ADDRESS,address)
        db.insert(TABLE_CUSTOMER,null,values)
        Log.i("SKHST_DB","Insertion COMMAND success @addCustomerData")
        db.close()
    }

    fun addHistoryData(fromUser:String,toUSer:String,date:String,amount:String,status:String){

        val values = ContentValues()
        val db = this.writableDatabase

        values.put(COLUMN_FROMUSER,fromUser)
        values.put(COLUMN_TOUSER,toUSer)
        values.put(COLUMN_DATE,date)
        values.put(COLUMN_AMOUNT,amount)
        values.put(COLUMN_STATUS,status)
        db.insert(TABLE_HISTORY,null,values)
        Log.i("SKHST_DB","Insertion COMMAND success @addHistoryData")
        db.close()
    }

    fun getCustomerData(exceptionUserId : String = "") : ArrayList<Customer>{
        var customersList  = ArrayList<Customer>()
        var username : String
        var about : String
        var profileImage : ByteArray
        var profileAvatar : ByteArray
        var uid : String
        var balance : String
        var email : String
        var mobile : String
        var address : String

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_CUSTOMER",null)
        while (cursor.moveToNext()){
            username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME))
            about = cursor.getString(cursor.getColumnIndex(COLUMN_ABOUT))
            profileImage = cursor.getBlob(cursor.getColumnIndex(COLUMN_PROFILEIMAGE))
            profileAvatar = cursor.getBlob(cursor.getColumnIndex(COLUMN_PROFILEAVATAR))
            uid = cursor.getString(cursor.getColumnIndex(COLUMN_UID))
            balance = cursor.getString(cursor.getColumnIndex(COLUMN_BALANCE))
            email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
            mobile = cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE))
            address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS))

            val tempObj = Customer(username,
                about,
                profileImage,
                profileAvatar,
                uid,
                balance.toDouble(),
                email,
                mobile,
                address)
            if(uid != exceptionUserId){
                customersList.add(tempObj)
            }
        }
        cursor.close()
        return customersList
    }

    fun getHistoryData() : ArrayList<HistoryData>{
        var HistoryList  = ArrayList<HistoryData>()
        var fromUser: String
        var toUSer: String
        var date: String
        var amount: String
        var status: String

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_HISTORY",null)
        while (cursor.moveToNext()){
            fromUser = cursor.getString(cursor.getColumnIndex(COLUMN_FROMUSER))
            toUSer = cursor.getString(cursor.getColumnIndex(COLUMN_TOUSER))
            date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE))
            amount = cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT))
            status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS))

            val tempObj = HistoryData(fromUser,toUSer, date, amount, status)
            HistoryList.add(tempObj)
        }
        cursor.close()
        try{
            HistoryList = HistoryList.reversed() as ArrayList<HistoryData>
        }catch (e : Exception){
            Log.e("SKHST_DB_4568746","Failed to reverse HistoryList")
        }
        return HistoryList
    }

    fun getUser(uid : String) : Customer{
        val db = this.readableDatabase
        val id = "\"$uid\""
        var tempObj : Customer

        var username : String
        var about : String
        var profileImage : ByteArray
        var profileAvatar : ByteArray
        var uid : String
        var balance : String
        var email : String
        var mobile : String
        var address : String

        val cursor = db.rawQuery("SELECT * FROM $TABLE_CUSTOMER WHERE $COLUMN_UID = $id",null)
        if(cursor!=null){
            cursor.moveToFirst()
        }
            username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME))
            about = cursor.getString(cursor.getColumnIndex(COLUMN_ABOUT))
            profileImage = cursor.getBlob(cursor.getColumnIndex(COLUMN_PROFILEIMAGE))
            profileAvatar = cursor.getBlob(cursor.getColumnIndex(COLUMN_PROFILEAVATAR))
            uid = cursor.getString(cursor.getColumnIndex(COLUMN_UID))
            balance = cursor.getString(cursor.getColumnIndex(COLUMN_BALANCE))
            email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
            mobile = cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE))
            address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS))
            tempObj = Customer(username,
                about,
                profileImage,
                profileAvatar,
                uid,
                balance.toDouble(),
                email,
                mobile,
                address)
        cursor.close()
        return tempObj
        }

    fun getIndividualHistoryData(uName : String) : ArrayList<HistoryData>{
        var HistoryList  = ArrayList<HistoryData>()
        var fromUser: String
        var toUSer: String
        var date: String
        var amount: String
        var status: String

        val id = "\"$uName\""

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_HISTORY WHERE $COLUMN_FROMUSER = $id",null)
        while (cursor.moveToNext()){
            fromUser = cursor.getString(cursor.getColumnIndex(COLUMN_FROMUSER))
            toUSer = cursor.getString(cursor.getColumnIndex(COLUMN_TOUSER))
            date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE))
            amount = cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT))
            status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS))

            val tempObj = HistoryData(fromUser,toUSer, date, amount, status)
            HistoryList.add(tempObj)
        }
        cursor.close()
        try{
            HistoryList = HistoryList.reversed() as ArrayList<HistoryData>
        }catch (e : Exception){
            Log.e("SKHST_DB_4568746","Failed to reverse HistoryList")
        }
        return HistoryList
    }

    fun setUserBalance(uid : String , amount : String){
        val db = this.writableDatabase
        val id = "\"$uid\""
        val am = "\"$amount\""
        val query = ("UPDATE $TABLE_CUSTOMER SET $COLUMN_BALANCE = $am WHERE $COLUMN_UID = $id")
        db.execSQL(query)
        Log.i("SKHST_15973_DB","Updated user balance!")
        db.close()
    }



}