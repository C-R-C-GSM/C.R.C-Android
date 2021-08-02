package com.example.crc_android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    //회원가입 순서
    val flag get() = _flag
    private val _flag : MutableLiveData<Int> = MutableLiveData<Int>()

    init {
        _flag.value = 0
    }


    fun plusFlag(){
        _flag.value = _flag.value?.plus(1)
    }
}