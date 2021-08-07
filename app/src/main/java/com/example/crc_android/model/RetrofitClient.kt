package com.example.crc_android.model

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)

            //서버 연결 시도 시간 설정
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
    }.build()

    val gson = GsonBuilder()
        .setLenient()
        .create()

    fun getService() : Retrofit = Retrofit.Builder()
        .baseUrl(RetrofitObject.BASE_URL_API)
        //json을 자바로 변환 (gson)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}