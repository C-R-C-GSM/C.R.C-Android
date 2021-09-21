package com.example.crc_android.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp



@HiltAndroidApp
class App : Application(){

    companion object {
        private lateinit var crcApplication: App
        fun getInstance() : App = crcApplication
        var title : String=""
        var date : String = ""
        var content : String = ""


    }


    override fun onCreate(){
        super.onCreate()
        crcApplication = this
    }
}