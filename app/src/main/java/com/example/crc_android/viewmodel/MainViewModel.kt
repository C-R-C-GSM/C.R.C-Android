package com.example.crc_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crc_android.Data
import com.example.crc_android.data.RegistNotice

class MainViewModel: ViewModel(){
    var lst = MutableLiveData<ArrayList<RegistNotice>>()

    fun add(blog:RegistNotice){
        Data.dataList.add(blog)
        lst.value = Data.dataList
    }

    fun remove(blog: RegistNotice){
        Data.dataList.remove(blog)
        lst.value = Data.dataList
    }

    fun get(){
        lst.value = Data.dataList
    }
}

