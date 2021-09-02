package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.adapter.RegisterMyAdapter
import com.example.crc_android.data.ADMINNOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.databinding.ActivityAdminnoticeBinding
import com.example.crc_android.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_adminnotice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdminnoticeActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    lateinit var binding : ActivityAdminnoticeBinding
    lateinit var adapter : RegisterMyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminnotice)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_adminnotice)
        binding.admin = this
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.recyclerviewAdminmain.adapter?.notifyDataSetChanged()
        setRetrofit()
        initialiseAdapter()
        binding.pluscontentBtn.setOnClickListener {
            val pluscontentIntent = Intent(this, NoticeregistActivity::class.java)
            startActivity(pluscontentIntent)
        }

        binding.backfragment.setOnClickListener {
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
        binding.recyclerviewAdminmain.layoutManager = viewManager
        observeData()
        viewModel.get()
        binding.recyclerviewAdminmain.adapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        viewModel.get()
    }

    fun observeData() {
        viewModel.lst.observe(this, Observer {
            Log.i("data", it.toString())
            binding.recyclerviewAdminmain.adapter = RegisterMyAdapter(viewModel, it, this)
        })
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






