package com.example.crc_android.data.network

import com.example.crc_android.data.models.FriendUserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendApi {

    @GET("check/one")
    fun getFriendOne(
        @Query("token") token: String
    ) : Response<FriendUserData>

    @GET("check/two")
    fun getFriendTwo(
        @Query("token") token: String
    ): Response<FriendUserData>

    @GET("check/three")
    fun getFriendThree(
        @Query("token") token: String
    ): Response<FriendUserData>
}