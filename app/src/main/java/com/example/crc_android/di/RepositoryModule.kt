package com.example.crc_android.di

import com.example.crc_android.data.repository.LoginRepository
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
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginDataSource: LoginDataSource
    ) : LoginRepository {
        return LoginRepository(loginDataSource)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(
        registerDataSource: RegisterDataSource
    ) : RegisterRepository {
        return RegisterRepository(registerDataSource)
    }
}