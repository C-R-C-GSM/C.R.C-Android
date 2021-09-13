package com.example.crc_android.data.network.model

import com.google.gson.annotations.SerializedName

data class ReviewPostRequest(
    val review_star: Int,
    val content: String,
    val nickname: String,
    @SerializedName("when") val When: String,
    val time:String
)