package com.example.crc_android.view.viewmore

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.adapter.MyAdapter
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.R
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
        val arrow1 : ImageView = findViewById(R.id.arrow1)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerview_main).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

            backarrow.setOnClickListener {
                val backarrowIntent = Intent(context, ViewmoreActivity::class.java)
                context.startActivity(backarrowIntent)
                finish()
            }
            arrow1.setOnClickListener {
                val arrow1Intent = Intent(context, NoticecontentActivity::class.java)
                context.startActivity(arrow1Intent)
                finish()
            }
        }

        setRetrofit()



    }
    private fun setRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-3-34-189-53.ap-northeast-2.compute.amazonaws.com:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

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
