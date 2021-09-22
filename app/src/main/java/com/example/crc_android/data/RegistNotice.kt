package com.example.crc_android.data

data class RegistNotice(
    val title: String,
    val content: String,
    val time: Long=System.currentTimeMillis()
)
