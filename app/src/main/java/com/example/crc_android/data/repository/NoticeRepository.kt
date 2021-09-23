package com.example.crc_android.data.repository

import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RegistNotice
import retrofit2.Response
import javax.inject.Inject

class NoticeRepository @Inject constructor(private val notice: NOTICE) {

    suspend fun getNoticeToken(token: String): Response<NoticeToken> = notice.getNoticeToken(token)

    suspend fun postNoticeToken(token: String, registNotice : RegistNotice) : Response<NoticeToken> = notice.postNoticeToken(token, registNotice)
}