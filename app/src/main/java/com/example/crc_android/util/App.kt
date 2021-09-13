package com.example.crc_android.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp



@HiltAndroidApp
class App : Application(){

    companion object {
        private lateinit var crcApplication: App
        fun getInstance() : App = crcApplication
    }


    override fun onCreate(){
        super.onCreate()
        crcApplication = this
    }


}