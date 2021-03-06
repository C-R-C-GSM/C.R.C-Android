package com.example.crc_android.data.network.api

import com.example.crc_android.data.model.dto.ResponseMessageDTO
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {
    @FormUrlEncoded
    @POST("login")
    suspend fun transferLogin(

        @Field("email") email : String,
        @Field("password") password : String,

        ): Response<ResponseMessageDTO?>
}