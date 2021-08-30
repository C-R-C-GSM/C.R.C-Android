package com.example.crc_android.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface NOTICE{
     @GET("notice/check")
    fun getnoticetoken(@Header("Authorization") token : String
     ):Call<NoticeToken>
}