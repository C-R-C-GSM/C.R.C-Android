package com.example.crc_android.di

import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.network.api.AdminApi
import com.example.crc_android.data.network.api.FriendApi
import com.example.crc_android.data.network.api.ReviewApi
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


    @Provides
    @Singleton
    fun provideApiReviewService(retrofit: Retrofit): ReviewApi {
        return retrofit.create(ReviewApi::class.java)
    }
    @Provides
    @Singleton
    fun provideApAdminService(retrofit: Retrofit): AdminApi {
        return retrofit.create(AdminApi::class.java)
    }
    @Provides
    @Singleton
    fun provideApiNoticeService(retrofit: Retrofit) : NOTICE {
        return retrofit.create(NOTICE::class.java)
    }

}