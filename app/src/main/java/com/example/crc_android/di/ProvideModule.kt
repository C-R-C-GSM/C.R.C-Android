package com.example.crc_android.di

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
        return retrofit.create(FriendApi::class.java)
    }
}