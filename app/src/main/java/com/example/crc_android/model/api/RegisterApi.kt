package com.example.crc_android.model.api


import com.example.crc_android.model.dto.ResponseMessageDTO
import retrofit2.Response
import retrofit2.http.*

interface RegisterApi {
    @FormUrlEncoded
    @POST("register")
    suspend fun transferRegister(

        @Field("email") email : String?,
        @Field("password") password : String?,
        @Field("name") name : String?,
        @Field("student_data") student_data : String?,

    ): Response<ResponseMessageDTO?>
}