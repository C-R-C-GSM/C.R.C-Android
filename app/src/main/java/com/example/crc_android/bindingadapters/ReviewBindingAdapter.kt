package com.example.crc_android.bindingadapters

import android.media.Rating
import android.widget.RatingBar
import androidx.databinding.BindingAdapter

object ReviewBindingAdapter{

    @BindingAdapter("reviewRating")
    @JvmStatic
    fun reviewRating(rating: RatingBar, data:Double){
        rating.rating = data.toFloat()
    }
}