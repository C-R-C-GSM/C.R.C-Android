package com.example.crc_android.data.network.api

import com.example.crc_android.data.network.model.FriendTotalData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendApi {


    @GET("check/total")
    suspend fun getFriendTotal(
        @Query("token") token : String
    ) : Response<FriendTotalData>
}