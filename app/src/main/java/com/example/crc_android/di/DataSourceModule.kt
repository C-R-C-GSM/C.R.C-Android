package com.example.crc_android.di

import com.example.crc_android.data.network.api.LoginApi
import com.example.crc_android.data.network.api.RegisterApi
import com.example.crc_android.data.repository.RegisterRepository
import com.example.crc_android.data.repository.datasource.LoginDataSource
import com.example.crc_android.data.repository.datasource.RegisterDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLoginDataSource(
        loginApi: LoginApi
    ) : LoginDataSource {
        return LoginDataSource(loginApi)
    }

    @Provides
    @Singleton
    fun provideRegisterDataSource(
        registerApi: RegisterApi
    ) : RegisterDataSource {
        return RegisterDataSource(registerApi)
    }
}