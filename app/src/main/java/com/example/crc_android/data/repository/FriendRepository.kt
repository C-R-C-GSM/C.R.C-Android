package com.example.crc_android.data.repository

import com.example.crc_android.data.models.Data
import com.example.crc_android.data.models.FriendUserData
import com.example.crc_android.data.models.FriendTotalData
import com.example.crc_android.data.network.FriendApi
import retrofit2.Response
import javax.inject.Inject

class FriendRepository @Inject constructor(
    private val friendApi: FriendApi
) {
    suspend fun getFriendOne(token: String): Response<FriendUserData> =
        friendApi.getFriendOne(token)

    suspend fun getFriendTwo(token: String): Response<FriendUserData> =
        friendApi.getFriendTwo(token)

    suspend fun getFriendThree(token: String): Response<FriendUserData> =
        friendApi.getFriendThree(token)
        
      suspend fun totalFriend(token:String): Response<FriendTotalData> =
        friendApi.getFriendTotal(token)
    }


