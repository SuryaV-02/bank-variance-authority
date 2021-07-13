package com.sparrow.bankvarianceauthority

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class donorUserListAdapter(val context : Context,val items : ArrayList<Customer>):RecyclerView.Adapter<donorUserListAdapter.ViewHolder>(){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val iv_profileImg = view.findViewById<ImageView>(R.id.iv_profileImg)
        val tv_profileName = view.findViewById<TextView>(R.id.tv_profileName)
        val iv_profileAva = view.findViewById<ImageView>(R.id.iv_profileAva)
        val csl_userTile = view.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.csl_userTile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_user_tile_row,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userName = items[position].userName
        val profileImage: ByteArray? = items[position].profileImage
        val profileAva : ByteArray? = items[position].profileAvatar
        holder.iv_profileAva.setImageBitmap(DbBitmapUtility.getImage(profileAva))
        holder.tv_profileName.text = userName
        holder.iv_profileImg.setImageBitmap(DbBitmapUtility.getImage(profileImage))
//        holder.iv_profileImg.setOnClickListener {
//            val intent = Intent(it.context,userDetails::class.java)
//            intent.putExtra("uid",items[position].UID)
//            it.context.startActivity(intent)
//        }
        holder.csl_userTile.setOnClickListener{
            val intent = Intent(it.context,userDetails::class.java)
            intent.putExtra("uid",items[position].UID)
            it.context.startActivity(intent)
        }
        setFadeAnimation(holder.itemView)
    }
    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }
}