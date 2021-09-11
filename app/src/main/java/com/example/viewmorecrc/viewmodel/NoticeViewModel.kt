package com.example.viewmorecrc.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewmorecrc.Data
import com.example.viewmorecrc.data.RegistNotice


class NoticeViewModel: ViewModel(){
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
