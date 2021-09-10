package com.example.crc_android.data.network.api

import com.example.crc_android.data.network.model.FriendUserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface FriendApi {

    @GET("/check/one")
    suspend fun getFriendOne(
        @Header("Token") token: String
    ) : Response<FriendUserData>

    @GET("/check/two")
    suspend fun getFriendTwo(
        @Header("Token") token: String
    ): Response<FriendUserData>

    @GET("/check/three")
    suspend fun getFriendThree(
        @Header("Token") token: String
    ): Response<FriendUserData>
}