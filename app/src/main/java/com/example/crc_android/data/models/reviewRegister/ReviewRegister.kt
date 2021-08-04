package com.example.crc_android.data.models.reviewRegister

data class ReviewRegister(
    val code: Int,
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)