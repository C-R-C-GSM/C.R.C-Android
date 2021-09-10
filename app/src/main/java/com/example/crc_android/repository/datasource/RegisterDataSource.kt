package com.example.crc_android.repository.datasource

import com.example.crc_android.model.api.RegisterApi
import javax.inject.Inject

class RegisterDataSource @Inject constructor(
    private val registerApi: RegisterApi
) {
    suspend fun registerApi(email:String,password:String, name :String, student_data : String) = registerApi.transferRegister(email, password, name, student_data)
}