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
        var nickname : String = ""
        var reply : String = ""
        var id : Int = -1
    }


    override fun onCreate(){
        super.onCreate()
        crcApplication = this
    }
}