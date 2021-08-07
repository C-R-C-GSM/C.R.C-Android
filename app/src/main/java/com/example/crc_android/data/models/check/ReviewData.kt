package com.example.crc_android.data.models.check

data class ReviewData(
    val content: String,
    val empathy: Int,
    val nickname: String,
    val reply: String,
    val review_star: Int,
    val review_time: String,
    val review_when: String,
    val reviewid: Int
)