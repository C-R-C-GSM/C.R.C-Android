package com.example.crc_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crc_android.data.RegistNotice

class MainViewModel: ViewModel(){
    var lst = MutableLiveData<ArrayList<RegistNotice>>()
    var newlist = ArrayList<RegistNotice>()

    fun add(blog:RegistNotice){
        newlist.add(blog)
        lst.value = newlist
    }

    fun remove(blog: RegistNotice){
        newlist.remove(blog)
        lst.value = newlist
    }

    fun get(){
        lst.value = newlist
    }
}

