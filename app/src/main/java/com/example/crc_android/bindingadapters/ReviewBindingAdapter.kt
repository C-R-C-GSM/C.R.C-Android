package com.example.crc_android.bindingadapters

import android.media.Rating
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

object ReviewBindingAdapter{

    @BindingAdapter("reviewRating")
    @JvmStatic
    fun reviewRating(rating: RatingBar, data:Double){
        rating.rating = data.toFloat()
    }

    @BindingAdapter("app:userDay")
    @JvmStatic
    fun getUserDay(text: TextView, day: Long) {

        val toTimeStamp = Date(day)
        val date = SimpleDateFormat("yyyy년 MM월 dd일 aa", Locale.forLanguageTag("ko"))
        text.text = date.format(toTimeStamp)
    }

}