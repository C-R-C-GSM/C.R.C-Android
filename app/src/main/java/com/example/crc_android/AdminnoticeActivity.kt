package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.adapter.RegisterMyAdapter
import com.example.crc_android.data.ADMINNOTICE
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.model.MainViewModel
import kotlinx.android.synthetic.main.activity_adminnotice.*
import kotlinx.android.synthetic.main.activity_noticeregist.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AdminnoticeActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    private lateinit var but: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminnotice)
        mainrecycler = findViewById(R.id.recyclerview_main)
        val application = requireNotNull(this).application
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        but = findViewById(R.id.update)
        but.setOnClickListener {
            addData()
        }
        initialiseAdapter()

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

    private fun initialiseAdapter() {
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    fun observeData() {
        viewModel.lst.observe(this, Observer {
            Log.i("data", it.toString())
            mainrecycler.adapter = RegisterMyAdapter(viewModel, it, this)
        })
    }

    fun addData() {
        var txtplce = findViewById<EditText>(R.id.title)
        var txtplace = findViewById<EditText>(R.id.content)
        var title = txtplce.text.toString()
        var content = txtplace.text.toString()
        if (title.isNullOrBlank() && content.isNullOrBlank()) {
            Toast.makeText(this, "Enter value!", Toast.LENGTH_LONG).show()
        } else {
            var blog = RegistNotice(title, content)
            viewModel.add(blog)
            txtplce.text.clear()
            txtplace.text.clear()
            mainrecycler.adapter?.notifyDataSetChanged()
        }
    }





    private fun setRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-3-34-189-53.ap-northeast-2.compute.amazonaws.com:3000/")
            .addConverterFactory(GsonConverterFactory.create()).client(client)
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




