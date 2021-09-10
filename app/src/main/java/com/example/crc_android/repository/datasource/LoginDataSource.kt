package com.example.crc_android.repository.datasource

import com.example.crc_android.model.api.LoginApi
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginApi: LoginApi
) {
    suspend fun loginApi(email: String, password:String) = loginApi.transferLogin(email, password)
}