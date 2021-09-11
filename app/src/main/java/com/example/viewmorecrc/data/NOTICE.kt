package com.example.viewmorecrc.data

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