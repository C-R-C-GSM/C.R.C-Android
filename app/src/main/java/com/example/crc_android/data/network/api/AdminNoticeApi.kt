package com.example.crc_android.data.network.api

import com.example.crc_android.data.NoticeToken
import retrofit2.Call
import retrofit2.http.*

interface AdminNoticeApi   {
    @GET("notice/register")
    fun getadminoticetoken(@Header("Authorization") token : String
    ): Call<NoticeToken>

   /* @POST("notice/register")
    fun getpost(@FieldMap param:HashMap<String, String>:Call<>)*/
}
