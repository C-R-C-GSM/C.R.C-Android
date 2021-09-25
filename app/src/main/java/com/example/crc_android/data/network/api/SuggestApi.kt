package com.example.crc_android.data.network.api

import com.example.crc_android.data.network.model.suggest.RegistToken
import com.example.crc_android.data.network.model.suggest.RegisterRequest
import com.example.crc_android.data.network.model.suggest.SuggestReplyRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SuggestApi {
    @GET("suggest/check")
   suspend fun getSuggestToken(@Header("Token") token : String
    ): Response<RegistToken>

   @POST("suggest/reply")
   suspend fun postReply(@Header("Token")token: String,
                         @Body request:SuggestReplyRequest
   ):Response<RegistToken>

   @POST("suggest/register")
   suspend fun postRegister(
       @Header("Token")token: String,
       @Body request:RegisterRequest
   ):Response<RegistToken>
}