package com.example.crc_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.crc_android.model.LoginAPI
import com.example.crc_android.model.DTO.ResponseMessageDTO
import com.example.crc_android.model.RetrofitClient
import retrofit2.Response

class LoginViewModel : ViewModel() {
    lateinit var loginResponse: LiveData<Response<ResponseMessageDTO>>

    //login email
    val email get() = _email
    private val _email: MutableLiveData<String> = MutableLiveData<String>()

    //login password
    val password get() = _password
    private val _password: MutableLiveData<String> = MutableLiveData<String>()


    fun setEmail(email: String) {
        this._email.value = email
    }

    fun setPassword(password: String) {
        this._password.value = password
    }


    fun loginApiCall() {

        loginResponse = liveData {

            val retService = RetrofitClient().getService().create(LoginAPI::class.java)
            val response = retService.transferLogin(
                email.value.toString(),
                password.value.toString()
            ) as Response<ResponseMessageDTO>

            emit(response)


        }

    }
}