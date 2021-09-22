package com.example.crc_android.data

import com.example.crc_android.data.network.model.ReviewReplyData
import com.example.crc_android.data.network.model.ReviewReplyRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface NOTICE{
    @GET("notice/check")
    fun getnoticetoken(@Header("Authorization") token : String
    ):Call<NoticeToken>


    @POST("notice/register")
     fun postnoticetoken( @Header("Token") token:String,
        @Body registNotice: RegistNotice
    ): Call<RegistNotice>
}