package com.example.crc_android.repository

import com.example.crc_android.repository.datasource.RegisterDataSource
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val registerDataSource: RegisterDataSource
) {
    suspend fun registerApi(email:String,password:String, name :String, student_data : String) = registerDataSource.registerApi(email, password, name, student_data)
}