package com.example.crc_android.data.network.api

import com.example.crc_android.data.network.model.check.CheckRoleData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AdminApi {


    @GET("check/role")
   suspend fun getCheckRole(
        @Header("Token") token: String
    ):Response<CheckRoleData>
}