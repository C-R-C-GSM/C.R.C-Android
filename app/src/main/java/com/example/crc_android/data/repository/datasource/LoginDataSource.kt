package com.example.crc_android.data.repository.datasource

import com.example.crc_android.data.network.api.LoginApi
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginApi: LoginApi
) {
    suspend fun loginApi(email: String, password:String) = loginApi.transferLogin(email, password)
}