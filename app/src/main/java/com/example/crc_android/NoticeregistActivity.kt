package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import kotlinx.android.synthetic.main.activity_noticeregist.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class NoticeregistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticeregist)

        setRetrofit()

        returnnotice.setOnClickListener {
            val returnnoticebtn = Intent(this,AdminnoticeActivity::class.java)
            startActivity(returnnoticebtn)
            finish()
        }

        update.setOnClickListener {
            val finishbtnIntent = Intent(this, AdminnoticeActivity::class.java)
            startActivity(finishbtnIntent)
            finish()
        }
    }
    private fun setRetrofit(){
        val retrofit = RetrofitHelper.getInstance()

        val service = retrofit.create(NOTICE::class.java)
        val call: Call<NoticeToken> = service.getnoticetoken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjM3LCJyb2xlIjowLCJpYXQiOjE2MjkzNTI0NzUsImV4cCI6MTYyOTM1NjA3NSwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.w78k_zbpqx14VwruONvOvbh3cmM_qZy35dZvu1cNXlI")
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



