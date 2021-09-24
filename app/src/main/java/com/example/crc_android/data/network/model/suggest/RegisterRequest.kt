package com.example.crc_android.data.network.model.suggest

import android.provider.ContactsContract

data class RegisterRequest (
    val title:String,
    val content :String,
    val nickname: String
        )