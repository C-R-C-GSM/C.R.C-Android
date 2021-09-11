package com.example.crc_android.viewmodel.login

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.*
import com.example.crc_android.data.model.dto.ResponseMessageDTO
import com.example.crc_android.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    val loginResponse : LiveData<Response<ResponseMessageDTO?>> get() = _loginResponse
    private val _loginResponse = MutableLiveData<Response<ResponseMessageDTO?>>()

    //텍스트 공백인지 체크 등
    val errorMessage : LiveData<String> get() = _errorMessage
    private val _errorMessage = MutableLiveData<String>()

    //login email
    val email get() = _email
    private val _email: MutableLiveData<String> = MutableLiveData<String>()

    //login password
    val password get() = _password
    private val _password: MutableLiveData<String> = MutableLiveData<String>()

    fun loginApiCall(email: String, password: String) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
            _errorMessage.value = "empty"
        else viewModelScope.launch {
            loginRepository.loginApi(email, password).let { response ->
                if (response.isSuccessful){
                    _loginResponse.value = response

                }
            }
        }
    }
}