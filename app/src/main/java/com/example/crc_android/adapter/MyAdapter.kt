package com.example.crc_android.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.data.NoticeList
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.databinding.FragmentNoticeBinding
import com.example.crc_android.util.App
import com.example.crc_android.view.viewmore.AdminnoticeFragment
import com.example.crc_android.view.viewmore.ViewmoreFragment
import java.text.SimpleDateFormat

class MyAdapter(val context: Fragment,
                val arrayList :ArrayList<NoticeList>
):

    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
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

        holder.itemtitle.setText(arrayList[position].notice_title)
        holder.itemdate.setText(arrayList[position].notice_time)

        holder.arrow1.setOnClickListener {
            App.title=arrayList[position].notice_title
            App.content=arrayList[position].notice_content
            App.date=arrayList[position].notice_time
            context.findNavController().navigate(R.id.action_noticeFragment_to_noticecontentFragment)
        }




    }



    override fun getItemCount(): Int {
        return arrayList.size
    }

}