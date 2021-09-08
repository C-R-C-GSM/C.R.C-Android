package com.example.crc_android.di

import com.example.crc_android.data.network.ReviewApi
import android.content.ContentValues
import android.util.Log
import com.example.crc_android.data.network.FriendApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): FriendApi {
        Log.d(ContentValues.TAG, "provideApiService: ")
        return retrofit.create(FriendApi::class.java)
    }


     @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ReviewApi {
        return retrofit.create(ReviewApi::class.java)
    }
}

