package com.example.crc_android.viewmodel.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.repository.LoginRepository
import com.example.crc_android.util.DataStoreModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dataStore: DataStoreModule
) : ViewModel() {
    val message: LiveData<String> get() = _message
    private val _message = MutableLiveData<String>()

    fun autologin() = viewModelScope.launch {
        val email = dataStore.readEmail.first()
        val password = dataStore.readPassword.first()
        Log.d("로그","자동로그인 - email : $email, password : $password")
        if (email == "" || password == "") {
            _message.value = "null"
        } else {
            loginApiCall(email, password)
        }
    }

    private fun loginApiCall(email: String, password: String) {
        viewModelScope.launch {
            loginRepository.loginApi(email, password).let { response ->
                if (response.isSuccessful) {
                    response.body()?.Token?.let { dataStore.setToken(it) }
                    _message.value = "login success"
                }else _message.value = "login fail"
            }
        }
    }
}