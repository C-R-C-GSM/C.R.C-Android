package com.example.crc_android.data.network.model

data class FriendUserData(
    val code: Int,
    val data: List<Data>,
    val message: String,
    val success: Boolean
)