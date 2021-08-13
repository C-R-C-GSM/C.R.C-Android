package com.example.crc_android.data.network

import com.example.crc_android.data.models.FriendTotalData
import retrofit2.Response
import retrofit2.http.GET

interface FriendApi {

    @GET("check/total")
   suspend fun getFriendTotal(
           token : String
    ) : Response<FriendTotalData>
}