package com.example.crc_android.viewmodel.login

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.model.dto.ResponseMessageDTO
import com.example.crc_android.data.repository.LoginRepository
import com.example.crc_android.util.DataStoreModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
   private val dataStore: DataStoreModule
) : ViewModel() {
    val readToken = dataStore.readToken
    val loginResponse: LiveData<Response<ResponseMessageDTO?>> get() = _loginResponse
    private val _loginResponse = MutableLiveData<Response<ResponseMessageDTO?>>()

    //텍스트 공백인지 체크 등
    val errorMessage: LiveData<String> get() = _errorMessage
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
                if (response.isSuccessful) {
                    _loginResponse.value = response

                }
            }
        }
    }
    // 토큰을 저장한다.
    fun saveToken(token: String) =
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("AdminViewModel", "saveToken: $token")
            dataStore.setToken(token)
        }
}