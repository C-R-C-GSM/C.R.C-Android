package com.example.crc_android.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.crc_android.data.network.model.Data

object FriendBindingAdapter {

    @BindingAdapter("studentsName")
    @JvmStatic
    fun studentsName(text: TextView, data: String) {
        text.text = data
    }


    @BindingAdapter("totalStudent")
    @JvmStatic
    fun totalStudent(text: TextView, data: List<Data>) {

        var sum = 0
        data.map {
            sum += it.student_check
        }

        text.text = sum.toString()

    }

    @BindingAdapter("studentsData")
    @JvmStatic
    fun studentsData(text: TextView, data: Int) {
        text.text = data.toString()
    }


}