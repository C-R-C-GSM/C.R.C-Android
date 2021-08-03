package com.example.crc_android.data.repository

import com.example.crc_android.data.models.ReviewRegister
import com.example.crc_android.data.network.ReviewApi
import retrofit2.Response
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val reviewApi: ReviewApi
) {
    suspend fun postReviewRegister(
        review_start: Int,
        content: String,
        nickname: String,
        When:Int
    ): Response<ReviewRegister> {
        return reviewApi.postReviewRegister(review_start, content, nickname, When)
    }

}