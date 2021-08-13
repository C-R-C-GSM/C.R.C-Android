package com.example.crc_android.data.network

import com.example.crc_android.data.models.check.ReviewCheck
import com.example.crc_android.data.models.reviewRegister.ReviewRegister
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ReviewApi {

    // 리뷰목록 확인
    @GET("review/check")
    suspend fun getReviewCheck(
        @Header("token") token:String

    ) :Response<ReviewCheck>




    @POST("review/register")
    suspend fun postReviewRegister(

        @Field("review_star") review_star: Int,
        @Field("content") content : String,
        @Field("nickname") nickname : String,
        @Field("when") When : Int,

        ) : Response<ReviewRegister>
}