package com.example.crc_android.data.network

import com.example.crc_android.data.models.Data
import com.example.crc_android.data.models.FriendUserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendApi {

    @GET("/check/one")
    suspend fun getFriendOne(
        @Query("Token") token: String
    ) : Response<FriendUserData>

    @GET("/check/two")
    suspend fun getFriendTwo(
        @Query("Token") token: String
    ): Response<FriendUserData>

    @GET("/check/three")
    suspend fun getFriendThree(
        @Query("Token") token: String
    ): Response<FriendUserData>
}