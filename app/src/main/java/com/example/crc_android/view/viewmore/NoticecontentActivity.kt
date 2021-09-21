package com.example.crc_android.view.viewmore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.crc_android.R
import com.example.crc_android.data.ADMINNOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RetrofitHelper
import com.example.crc_android.databinding.ActivityNoticecontentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticecontentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoticecontentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_noticecontent)
        binding.gettitle.setText(intent.getStringExtra("title"))
        binding.getdate.setText(intent.getStringExtra("date"))
        binding.getcontent.setText(intent.getStringExtra("content"))
        setRetrofit()

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

    private fun setRetrofit() {
        val retrofit = RetrofitHelper.getInstance()

        val service = retrofit.create(ADMINNOTICE::class.java)
        val call: Call<NoticeToken> = service.getadminoticetoken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjM3LCJyb2xlIjowLCJpYXQiOjE2MjkzNTI0NzUsImV4cCI6MTYyOTM1NjA3NSwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.w78k_zbpqx14VwruONvOvbh3cmM_qZy35dZvu1cNXlI")
        call.enqueue(object : Callback<NoticeToken> {
            override fun onFailure(call: Call<NoticeToken>, t: Throwable) {
                println("실패")
                Log.d("Test",t.toString())
            }

            override fun onResponse(call: Call<NoticeToken>, response: Response<NoticeToken>) {
                if(response.body()!=null){
                    println("성공")
                }
            }
        })


    }
}