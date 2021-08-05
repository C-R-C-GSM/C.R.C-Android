package com.example.crc_android.model


import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RegisterAPI {
    @FormUrlEncoded
/*    @Headers("accept: application/json",
        "content-type: application/json")*/
    @POST("register")
    suspend fun transferRegister(

        @Field("email") email : String,
        @Field("password") password : String,
        @Field("name") name : String,
        @Field("student_data") student_data : String,

       // @Field email: String, password: String, name: String, student_data: String

    ): Response<ResponseMessageDTO?>
}