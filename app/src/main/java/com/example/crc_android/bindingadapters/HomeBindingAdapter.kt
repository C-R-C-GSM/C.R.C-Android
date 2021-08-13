package com.example.crc_android.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

object HomeBindingAdapter{

    @BindingAdapter("app:totalFriend")
    @JvmStatic
    fun totalFriend(data: Int, text:TextView){
        text.text=data.toString()
    }
}