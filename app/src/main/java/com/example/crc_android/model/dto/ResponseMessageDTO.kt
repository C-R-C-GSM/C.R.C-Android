package com.example.crc_android.model.dto

data class ResponseMessageDTO(
    val success : String,
    val code : String,
    val message : String,
    val Token : String?
)