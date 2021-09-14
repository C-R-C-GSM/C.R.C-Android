package com.example.crc_android.data.network.model.reviewRegister

import com.example.crc_android.data.network.model.Data

data class ReviewRegister(
    val code: Int,
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)