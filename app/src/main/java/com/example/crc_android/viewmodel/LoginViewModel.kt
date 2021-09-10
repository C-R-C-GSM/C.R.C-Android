package com.example.crc_android.viewmodel

import androidx.lifecycle.*
import com.example.crc_android.model.dto.ResponseMessageDTO
import com.example.crc_android.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    val loginResponse : LiveData<Response<ResponseMessageDTO?>> get() = _loginResponse
    private val _loginResponse = MutableLiveData<Response<ResponseMessageDTO?>>()

    //login email
    val email get() = _email
    private val _email: MutableLiveData<String> = MutableLiveData<String>()

    //login password
    val password get() = _password
    private val _password: MutableLiveData<String> = MutableLiveData<String>()

    fun loginApiCall(email: String, password: String) = viewModelScope.launch {
        loginRepository.loginApi(email, password).let { response ->
            _loginResponse.value = response
        }
    }
}