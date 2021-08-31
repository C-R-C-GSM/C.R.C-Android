package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_viewmore.*

class ViewmoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmore)

        noticeBtn.setOnClickListener {
            val noticeBtn = Intent(this,AdminnoticeActivity::class.java )
            startActivity(noticeBtn)
            finish()
        }
    }
}