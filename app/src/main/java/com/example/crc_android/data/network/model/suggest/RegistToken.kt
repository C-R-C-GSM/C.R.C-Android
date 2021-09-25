package com.example.crc_android.data.network.model.suggest

data class RegistToken(
    val success : Boolean,
    val code : Int,
    val message : String,
    val suggest_data : ArrayList<RegistList>? = null
)

data class RegistList(
    val suggestid : Int,
    val title : String,
    val content : String,
    val suggest_time : String,
    val nickname : String,
    val reply : String
)