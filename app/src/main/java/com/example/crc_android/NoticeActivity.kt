package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.adapter.MyAdapter
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class NoticeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        viewManager = LinearLayoutManager(this, RecyclerView.VERTICAL, true)
        viewAdapter = MyAdapter(this)
        val backarrow : ImageView= findViewById(R.id.backarrow)


        recyclerView = findViewById<RecyclerView>(R.id.recyclerview_main).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

            backarrow.setOnClickListener {
                val backarrowIntent = Intent(context, ViewmoreActivity::class.java)
                context.startActivity(backarrowIntent)
                finish()
            }
        }

        setRetrofit()



    }
    private fun setRetrofit() {
        val retrofit = RetrofitHelper.getInstance()

        val service = retrofit.create(NOTICE::class.java)
        val call: Call<NoticeToken> = service.getnoticetoken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjM3LCJyb2xlIjowLCJpYXQiOjE2Mjk4NDY4NTQsImV4cCI6MTYyOTg1MDQ1NCwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.NsrgLDyvtiAuKeTvxTx3r7X9A4i94afveic2iBiXLMg")
        call.enqueue(object :Callback<NoticeToken>{
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