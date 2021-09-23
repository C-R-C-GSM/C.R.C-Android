package com.example.crc_android.viewmodel.suggest


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crc_android.data.Data
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.data.network.model.suggest.RegistData

class RegistractionViewModel: ViewModel(){
    var lst = MutableLiveData<ArrayList<RegistData>>()

    fun add(blog: RegistData){
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