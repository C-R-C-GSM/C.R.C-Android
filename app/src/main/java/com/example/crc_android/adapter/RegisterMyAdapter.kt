package com.example.crc_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.databinding.CardNoticeBinding
import com.example.crc_android.model.MainViewModel

class RegisterMyAdapter(
    val viewModel:MainViewModel,
    val arrayList :ArrayList<RegistNotice>,
    val context : Context
    ):RecyclerView.Adapter<RegisterMyAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RegisterMyAdapter.ViewHolder {

        val binding = CardNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        return viewHolder

    }

    inner class ViewHolder(val binding : CardNoticeBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.arrow1.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = arrayList.size

}