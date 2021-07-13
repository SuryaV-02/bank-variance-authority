package com.sparrow.bankvarianceauthority

class Customer(
    var userName: String?,
    var about: String?,
    var profileImage: ByteArray?,
    var profileAvatar: ByteArray?,
    var UID: String?,
    var balance: Double?,
    var email: String?,
    var mobile: String,
    var address: String?
){
    fun setCurrentBalance(newBalance:Double?){
        this.balance = newBalance
    }
    fun getCurrentBalance() : Double?{
        return this.balance
    }




//    fun createUser(userName : String?,profileImage : ByteArray? ,profileAvatar : ByteArray?,UID:String?,balance:Double?,
//                   email:String?,mobile:Long?,address:String?){
//        this.userName = userName
//        this.profileImage = profileImage
//        this.profileAvatar = profileAvatar
//        this.UID = UID
//        this.balance = balance
//        this.email = email
//        this.mobile = mobile
//        this.address = address
//    }
}