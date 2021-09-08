package com.example.crc_android.data.network.model

import com.google.gson.annotations.SerializedName

data class ReviewReplyRequest(val reply:String, @SerializedName("reviewid") val reviewId:String)
