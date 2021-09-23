package com.example.crc_android.data

data class NoticeToken(
    val success : Boolean,
    val code : Int,
    val message : String,
    val notice_list : ArrayList<NoticeList>? = null
)

data class NoticeList(
    val noticeid : Int,
    val notice_title : String,
    val notice_content : String,
    val notice_time : String
)