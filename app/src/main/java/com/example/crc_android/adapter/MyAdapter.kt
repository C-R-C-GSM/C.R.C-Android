package com.example.crc_android.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.AdminnoticeActivity
import com.example.crc_android.R

class MyAdapter(val context: Context):
        RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
            class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                public var itemtitle : TextView = itemView.findViewById(R.id.sampletext1)
                public var itemdate : TextView = itemView.findViewById(R.id.sampledate1)

                val arrow1: ImageView = itemView.findViewById(R.id.arrow1)


            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_notice,parent,false)
    return MyViewHolder(cardView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemtitle.setText("제목입니다")
        holder.itemdate.setText("날짜입니다")


        holder.arrow1.setOnClickListener {
            val arrow1Intent = Intent(context, AdminnoticeActivity::class.java)
            context.startActivity(arrow1Intent)
        }


    }



    override fun getItemCount(): Int {
        return 7
    }
        }