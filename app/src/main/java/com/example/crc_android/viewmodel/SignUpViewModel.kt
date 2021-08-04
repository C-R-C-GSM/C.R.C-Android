package com.example.crc_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {




    //회원가입 순서
    val flag get() = _flag
    private val _flag : MutableLiveData<Int> = MutableLiveData<Int>()
//
//    lateinit var email : String
//    lateinit var password : String
//    lateinit var name : String
//    lateinit var class_number : String

    //signup email
    val email get() = _email
    private val _email : MutableLiveData<String> = MutableLiveData<String>()

    //signup password
    val password get() = _password
    private val _password : MutableLiveData<String> = MutableLiveData<String>()

    //signup name
    val name get() = _name
    private val _name : MutableLiveData<String> = MutableLiveData<String>()

    //signup 학년, 반, 번호
    val classNumber get() = _classNumber
    private val _classNumber : MutableLiveData<Int> = MutableLiveData<Int>()

    //다음 프래그먼트로 넘어갈건지 확인
    val nextFragment get() = _nextFragment
    private val _nextFragment : MutableLiveData<Int> = MutableLiveData<Int>()

    //edittext 공백인지 확인
     var ifBlankCheck  = 1


    init {
        _flag.value = 0
        _email.value = "null"
        _password.value = "null"
        _name.value = "null"
        _classNumber.value = 0
    }


    fun setEmail(email:String){
        this._email.value = email
    }
    fun setPassword(password : String){
        this._password.value = password
    }
    fun setName(name:String){
        this._name.value = name
    }
    fun setClassNumber(classNumber:Int){
        this._classNumber.value = classNumber
    }

    fun setNextFragment(nextFragment:Int){
        this.nextFragment.value = nextFragment
    }

    fun plusFlag(){
        _flag.value = _flag.value?.plus(1)
    }
}