package com.example.crc_android.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    private lateinit var dataStore : DataStoreModule

    companion object {
        private lateinit var crcApplication: App
        fun getInstance() : App = crcApplication
    }


    override fun onCreate(){
        super.onCreate()
        crcApplication = this
        dataStore = DataStoreModule(this)
    }

    fun getDataStore() : DataStoreModule = dataStore

}