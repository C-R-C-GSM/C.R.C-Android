package com.example.crc_android.data.repository

import com.example.crc_android.data.network.api.FriendApi
import com.example.crc_android.data.network.model.FriendTotalData
import com.example.crc_android.data.network.model.FriendUserData
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

    suspend fun totalFriend(token: String): Response<FriendTotalData> {
        return friendApi.getFriendTotal(token)
    }
}
