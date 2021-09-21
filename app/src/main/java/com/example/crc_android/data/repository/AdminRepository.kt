package com.example.crc_android.data.repository

import com.example.crc_android.data.network.api.AdminApi
import com.example.crc_android.data.network.model.check.CheckRoleData
import retrofit2.Response
import javax.inject.Inject

class AdminRepository @Inject constructor(private val adminApi: AdminApi) {

    suspend fun getCheckRole(token: String): Response<CheckRoleData> = adminApi.getCheckRole(token)
}