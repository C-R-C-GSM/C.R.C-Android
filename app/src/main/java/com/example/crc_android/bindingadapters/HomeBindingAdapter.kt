package com.example.crc_android.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

object HomeBindingAdapter{

    @BindingAdapter("app:totalFriend")
    @JvmStatic
    fun totalFriend( text:TextView,data: Int,){

        if(data.toString().isNotEmpty())
        text.text=data.toString()
    }
}