package com.example.crc_android.data.network

import com.example.crc_android.data.network.model.check.ReviewCheck
import com.example.crc_android.data.network.model.reviewRegister.ReviewRegister
import com.example.crc_android.data.network.model.ReviewPostRequest
import com.example.crc_android.data.network.model.ReviewReplyRequest
import retrofit2.Response
import retrofit2.http.*

interface ReviewApi {

    // 리뷰목록 확인
    @GET("review/check")
    suspend fun getReviewCheck(
        @Header("Token") token: String

    ): Response<ReviewCheck>

    //리뷰 응답
    @POST("review/reply")
    suspend fun postReviewReply(

        @Header("Token") token:String,
        @Body reviewReply:ReviewReplyRequest
    )

    @POST("review/register")
    suspend fun postReviewRegister(

        @Header("Token") token:String,
        @Body review: ReviewPostRequest

    ): Response<ReviewRegister>
}