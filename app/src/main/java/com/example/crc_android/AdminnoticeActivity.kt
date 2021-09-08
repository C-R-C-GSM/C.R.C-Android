package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.crc_android.data.ADMINNOTICE
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import kotlinx.android.synthetic.main.activity_adminnotice.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdminnoticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminnotice)

        setRetrofit()

        pluscontentBtn.setOnClickListener {
            val pluscontentIntent = Intent(this, NoticeregistActivity::class.java)
            startActivity(pluscontentIntent)
            finish()
        }

        backfragment.setOnClickListener {
            val backfragmentbtn = Intent(this, ViewmoreActivity::class.java)
            startActivity(backfragmentbtn)
            finish()
        }
        /*nextarrow.setOnClickListener {
            val nextarrowbtn = Intent(this,NoticecontentActivity::class.java)
            startActivity(nextarrowbtn)
        }*/


    }
    private fun setRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ec2-3-35-81-230.ap-northeast-2.compute.amazonaws.com:3000/")
            .addConverterFactory(GsonConverterFactory.create()).client(createOkHttpClient())
            .build()

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
private fun createOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    builder.addInterceptor(interceptor)
    return builder.build()
}


