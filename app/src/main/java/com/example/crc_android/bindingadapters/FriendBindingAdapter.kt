package com.example.crc_android.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

object FriendBindingAdapter {

    @BindingAdapter("studentsName" )
    @JvmStatic
    fun studentsName(text: TextView, data: String) {
        text.text=data
    }


    @BindingAdapter("studentsData")
    @JvmStatic
    fun studentsData(text: TextView, data: Int) {
        text.text=data.toString()
    }


}