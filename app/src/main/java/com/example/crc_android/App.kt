package com.example.crc_android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

<<<<<<< HEAD

=======
>>>>>>> origin/develop
@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var instance: App
            private set

    }
    override fun onCreate(){
        super.onCreate()
        instance =this
    }
}