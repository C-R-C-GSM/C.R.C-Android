package com.example.crc_android.view.viewmore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.crc_android.R
import com.example.crc_android.databinding.ActivityViewmoreBinding

class ViewmoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewmoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_viewmore)

        binding.noticeBtn.setOnClickListener {
            val noticeBtn = Intent(this,AdminnoticeActivity::class.java )
            startActivity(noticeBtn)
            finish()
        }
    }
}