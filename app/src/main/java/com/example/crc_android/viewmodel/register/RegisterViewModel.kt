package com.example.crc_android.viewmodel.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.network.model.ResponseMessageDTO
import com.example.crc_android.data.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {

    //회원가입 순서
    val flag: LiveData<Int> get() = _flag
    private val _flag = MutableLiveData<Int>()

    //처음 회원가입 이메일 부분에서 뒤로가기 클릭
    val firstBackBtn: LiveData<Boolean> get() = _firstBackBtn
    private val _firstBackBtn = MutableLiveData<Boolean>()

    //signup email
    val email get() = _email
    private val _email: MutableLiveData<String> = MutableLiveData<String>()

    //signup password
    val password get() = _password
    private val _password: MutableLiveData<String> = MutableLiveData<String>()

    //signup name
    val name get() = _name
    private val _name: MutableLiveData<String> = MutableLiveData<String>()

    //signup 학년, 반, 번호
    val classNumber get() = _classNumber
    private val _classNumber: MutableLiveData<String> = MutableLiveData<String>()

    val registerResponse: LiveData<Response<ResponseMessageDTO?>> get() = _registerResponse
    private val _registerResponse = MutableLiveData<Response<ResponseMessageDTO?>>()


    init {
        _flag.value = 0
        _email.value = "null"
        _password.value = "null"
        _name.value = "null"
        _classNumber.value = "null"
    }


    fun setEmail(email: String) {
        this._email.value = email
    }

    fun setPassword(password: String) {
        this._password.value = password
    }

    fun setName(name: String) {
        this._name.value = name
    }

    fun setClassNumber(classNumber: String) {
        this._classNumber.value = classNumber
    }

    fun plusFlag() {
        _flag.value = _flag.value?.plus(1)
    }

    fun minusFlag() {
        _flag.value = _flag.value?.minus(1)
    }

    fun setFirstBackBtn(){
        _firstBackBtn.value = true
    }

    fun registerApiCall() =
        viewModelScope.launch {
            Log.d("로그","call regiserapicall : "+_email.value +_password.value + name.value + classNumber.value)
            repository.registerApi(_email.value, _password.value, _name.value, _classNumber.value).let { response ->
             _registerResponse.value = response
            }
        }
}