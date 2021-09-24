package com.example.crc_android.data.network.api

import com.example.crc_android.data.NoticeToken
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AdminNoticeApi   {
    @GET("notice/register")
    suspend fun getAdminNoticeToken(@Header("Authorization") token : String
    ):  Response<NoticeToken>

   /* @POST("notice/register")
    fun getpost(@FieldMap param:HashMap<String, String>:Call<>)*/
}
