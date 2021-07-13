package com.sparrow.bankvarianceauthority

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class historyAdapter(val context : Context, val items : ArrayList<HistoryData>):RecyclerView.Adapter<historyAdapter.ViewHolder>(){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val iv_status = view.findViewById<ImageView>(R.id.iv_status)
        val tv_date = view.findViewById<TextView>(R.id.tv_date)
        val tv_fromName = view.findViewById<TextView>(R.id.tv_fromName)
        val tv_toName = view.findViewById<TextView>(R.id.tv_toName)
        val tv_amount = view.findViewById<TextView>(R.id.tv_amount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_history_tile_row,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.iv_status.setImageBitmap(DbBitmapUtility.getImage(items[position].status))
        Log.i("SKHST45789325","${items[position].status} : ${items[position].status=="false"}")
        if(items[position].status=="false"){
            holder.iv_status.setImageResource(R.drawable.blue_cross)
        }else{
            holder.iv_status.setImageResource(R.drawable.blue_check)
        }


        holder.tv_date.text = items[position].date
        holder.tv_fromName.text = items[position].fromUser
        holder.tv_toName.text = items[position].toUSer
        holder.tv_amount.text = items[position].amount
    }
}