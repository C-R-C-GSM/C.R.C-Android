package com.example.crc_android.data.models.check

data class ReviewCheck(
    val code: Int,
    val message: String,
    val review_data: List<ReviewData>,
    val success: Boolean
)