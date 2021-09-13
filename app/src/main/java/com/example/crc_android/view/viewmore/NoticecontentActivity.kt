package com.example.crc_android.view.viewmore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.crc_android.R
import com.example.crc_android.databinding.ActivityNoticecontentBinding

class NoticecontentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoticecontentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_noticecontent)


        binding.returnbtn.setOnClickListener {
            val returnbtnIntent = Intent(this,NoticeActivity::class.java )
            startActivity(returnbtnIntent)
            finish()
        }

        binding.finishbtn.setOnClickListener {
            val finishbtnIntent = Intent(this,NoticeActivity::class.java)
            startActivity(finishbtnIntent)
            finish()
        }

    }
}