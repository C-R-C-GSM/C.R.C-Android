package com.example.crc_android.data.repository

import com.example.crc_android.data.network.model.check.ReviewCheck
import com.example.crc_android.data.network.model.reviewRegister.ReviewRegister
import com.example.crc_android.data.network.ReviewApi
import com.example.crc_android.data.network.model.ReviewPostRequest
import com.example.crc_android.data.network.model.ReviewReplyRequest
import retrofit2.Response
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val reviewApi: ReviewApi
) {
    suspend fun postReviewRegister(
        token:String,
   reviewPostRequest: ReviewPostRequest
    ): Response<ReviewRegister> {
        return reviewApi.postReviewRegister(token,reviewPostRequest)
    }

    suspend fun getReviewCheck(
        token:String
    ) : Response<ReviewCheck>{
        return reviewApi.getReviewCheck(token)
    }

    suspend fun postReviewReply(
        token:String,
        reviewReplyRequest: ReviewReplyRequest
    ) {
        return reviewApi.postReviewReply(token,reviewReplyRequest)
    }

}