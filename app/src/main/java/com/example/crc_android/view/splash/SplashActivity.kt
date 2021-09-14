package com.example.crc_android.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.util.extension.startActivityWithFinish
import com.example.crc_android.view.login.LoginActivity
import com.example.crc_android.viewmodel.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkUser()
        observeViewModel()
    }

    private fun observeViewModel(){
        splashViewModel.message.observe(this, Observer {
            when(it){
                "null" -> goLoginActivity()
                "login success" -> this@SplashActivity.startActivityWithFinish(this,MainActivity::class.java)
                "login fail" -> goLoginActivity()
            }
        })
    }

    private fun checkUser(){
        splashViewModel.autologin()
    }

    private fun goLoginActivity(){
        this@SplashActivity.startActivityWithFinish(this,LoginActivity::class.java)
    }
}