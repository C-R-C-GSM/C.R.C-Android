package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.databinding.ActivityNoticeregistBinding
import com.example.crc_android.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_noticeregist.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import java.util.concurrent.TimeUnit
import kotlin.math.log

class NoticeregistActivity : AppCompatActivity() {
    lateinit var binding: ActivityNoticeregistBinding
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private val TAG = "NoticeregistActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticeregist)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_noticeregist)
        binding.activity = this
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setRetrofit()

        binding.returnnotice.setOnClickListener {
            val returnnoticebtn = Intent(this, AdminnoticeActivity::class.java)
            startActivity(returnnoticebtn)
            finish()
        }

        binding.update.setOnClickListener {
            addData()
            finish()
        }
    }


    fun addData() {
        val title = binding.title.text.toString()
        val content = binding.content.text.toString()
        Log.d(TAG, "addData: 테스트")
        if (title.isNullOrBlank() && content.isNullOrBlank()) {
            Toast.makeText(this, "Enter value!", Toast.LENGTH_LONG).show()
        } else {
            var blog = RegistNotice(title, content)
            viewModel.add(blog)
            Log.d(TAG, "addData: $title")
            binding.title.text.clear()
            binding.content.text.clear()
        }
    }


    private fun setRetrofit() {
        val retrofit = RetrofitHelper.getInstance()

        val service = retrofit.create(NOTICE::class.java)
        val call: Call<NoticeToken> =
            service.getnoticetoken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjM3LCJyb2xlIjowLCJpYXQiOjE2MjkzNTI0NzUsImV4cCI6MTYyOTM1NjA3NSwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.w78k_zbpqx14VwruONvOvbh3cmM_qZy35dZvu1cNXlI")
        call.enqueue(object : Callback<NoticeToken> {
            override fun onFailure(call: Call<NoticeToken>, t: Throwable) {
                println("실패")
                Log.d("Test", t.toString())
            }

            override fun onResponse(call: Call<NoticeToken>, response: Response<NoticeToken>) {
                if (response.body() != null) {
                    println("성공")
                }
            }
        })


    }
}


val interceptor = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
}

val client = OkHttpClient.Builder().apply {
    this.addInterceptor(interceptor)

        //서버 연결 시도 시간 설정
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
}.build()




