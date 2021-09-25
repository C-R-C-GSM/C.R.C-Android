package com.example.crc_android.data.repository

import com.example.crc_android.data.network.api.SuggestApi
import com.example.crc_android.data.network.model.suggest.RegistToken
import com.example.crc_android.data.network.model.suggest.RegisterRequest
import com.example.crc_android.data.network.model.suggest.SuggestReplyRequest
import retrofit2.Response
import javax.inject.Inject

class SuggestRepository @Inject constructor(private val suggestApi: SuggestApi) {

    suspend fun getSuggetToken(token: String): Response<RegistToken> = suggestApi.getSuggestToken(token)

    suspend fun postReply(token: String, request: SuggestReplyRequest): Response<RegistToken> = suggestApi.postReply(token,request)

    suspend fun postRegister(token: String,request: RegisterRequest):Response<RegistToken> = suggestApi.postRegister(token,request)
}