package com.example.crc_android.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crc_android.data.RegistNotice

class MainViewModel: ViewModel(){
    var lst =MutableLiveData<ArrayList<RegistNotice>>()
    var newlist = arrayListOf<RegistNotice>()

    fun add(blog:RegistNotice){
        newlist.add(blog)
        lst.value =newlist
    }
}

