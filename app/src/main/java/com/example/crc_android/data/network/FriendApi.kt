package com.example.crc_android.data.network

import com.example.crc_android.data.models.Data
import com.example.crc_android.data.models.FriendUserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

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
  
    @GET("check/total")
   suspend fun getFriendTotal(
           token : String
    ) : Response<FriendTotalData>

}

