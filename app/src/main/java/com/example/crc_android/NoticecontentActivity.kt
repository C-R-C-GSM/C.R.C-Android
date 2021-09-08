package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_noticecontent.*

class NoticecontentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticecontent)

        returnbtn.setOnClickListener {
            val returnbtnIntent = Intent(this,NoticeActivity::class.java )
            startActivity(returnbtnIntent)
            finish()
        }

        finishbtn.setOnClickListener {
            val finishbtnIntent = Intent(this,NoticeActivity::class.java)
            startActivity(finishbtnIntent)
            finish()
        }
    }
}