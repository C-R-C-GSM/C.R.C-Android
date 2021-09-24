package com.example.crc_android.data

import com.example.crc_android.data.network.model.ReviewReplyData
import com.example.crc_android.data.network.model.ReviewReplyRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface NOTICE{
    @GET("notice/check")
    suspend fun getNoticeToken(@Header("Token") token : String
    ):Response<NoticeToken>


    @POST("notice/register")
    suspend fun postNoticeToken( @Header("Token") token:String,
        @Body registNotice: RegistNotice
    ): Response<NoticeToken>
}