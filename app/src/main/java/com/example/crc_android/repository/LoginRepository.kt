package com.example.crc_android.repository

import com.example.crc_android.repository.datasource.LoginDataSource
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginDataSource: LoginDataSource,
) {
    suspend fun loginApi(email: String, password: String) = loginDataSource.loginApi(email, password)
}