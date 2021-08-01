package com.example.crc_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        //fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.sign_up_frame_layout, SignUpEmailFragment())
        transaction.commit()
    }
}