package com.example.crc_android.data.repository


import com.example.crc_android.data.models.FriendTotalData
import com.example.crc_android.data.network.FriendApi
import retrofit2.Response
import javax.inject.Inject

class FriendRepository @Inject constructor(
    private val friendApi: FriendApi

) {

    suspend fun totalFriend(token:String): Response<FriendTotalData> {
        return friendApi.getFriendTotal(token)
    }


}