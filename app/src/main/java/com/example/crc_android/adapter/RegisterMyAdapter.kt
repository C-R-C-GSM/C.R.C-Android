package com.example.crc_android.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.databinding.CardNoticeBinding
import com.example.crc_android.util.App
import com.example.crc_android.view.viewmore.NoticecontentFragment
import com.example.crc_android.viewmodel.viewmore.MainViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList



class RegisterMyAdapter(
    val viewModel: MainViewModel,
    val arrayList :ArrayList<RegistNotice>,
    val context : Activity
): RecyclerView.Adapter<RegisterMyAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = CardNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)


        return holder
    }



    inner class ViewHolder(val binding : CardNoticeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note:RegistNotice){
            binding.data = note
            val date = Date(note.time)
            binding.sampledate1.text = SimpleDateFormat("yyyy-MM-dd").format(date)

            (context as MainActivity)
            binding.arrow1.setOnClickListener {
                App.title = binding.sampletext1.text.toString()
                App.date = binding.sampledate1.text.toString()
                App.content=note.content
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.navHostFragment, NoticecontentFragment()).commit()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int = arrayList.size

}