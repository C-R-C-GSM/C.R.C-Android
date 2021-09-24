package com.example.crc_android.data.repository

import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.network.api.AdminNoticeApi
import retrofit2.Response
import javax.inject.Inject

class AdminNoticeRepository @Inject constructor(private val adminNoticeApi: AdminNoticeApi) {

    suspend fun getCheckRole(token: String): Response<NoticeToken> = adminNoticeApi.getAdminNoticeToken(token)
}