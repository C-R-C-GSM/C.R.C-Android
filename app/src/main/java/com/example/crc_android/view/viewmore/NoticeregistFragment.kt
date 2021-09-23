package com.example.crc_android.view.viewmore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.data.RetrofitHelper
import com.example.crc_android.databinding.FragmentNoticeregistBinding
import com.example.crc_android.viewmodel.viewmore.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


class NoticeregistFragment : Fragment() {

    lateinit var binding: FragmentNoticeregistBinding
    private lateinit var viewModel: MainViewModel
    private val TAG = "NoticeregistActivity"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_noticeregist, container, false)
        binding.activity = this
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


        setRetrofit()

        (requireActivity() as MainActivity)
        binding.returnnotice.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction().addToBackStack(null)
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, AdminnoticeFragment()).commit()
        }

        binding.update.setOnClickListener {
            addData()
        }
        return binding.root

    }

    private fun addData() {
        val title = binding.puttitle.text.toString()
        val content = binding.putcontent.text.toString()
        Log.d(TAG, "addData: 테스트 $title $content")
        if ((title.isBlank() || content.isBlank())) {
            Toast.makeText(requireContext(), "문자가 없어용~~~", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "addData: $title")
        } else {
            var blog = RegistNotice(title, content)
            viewModel.add(blog)
            binding.puttitle.text.clear()
            binding.putcontent.text.clear()
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction().addToBackStack(null)
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, AdminnoticeFragment()).commit()
        }
    }


    private fun setRetrofit() {
        val retrofit = RetrofitHelper.getInstance()

        val service = retrofit.create(NOTICE::class.java)

        val post : Call<RegistNotice> =
        service.postNoticeToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjI3LCJyb2xlIjoxLCJpYXQiOjE2MzIyOTcwMjcsImV4cCI6MTYzMjMwMDYyNywiaXNzIjoiQy5SLkNfU0VSVkVSIn0.GzY30og-8rKAifp-NZnl6_aVe6lX0p9mxdpikl8ZVPQ",RegistNotice(title="String",content = "String"))
        post.enqueue(object :Callback<RegistNotice>{
            override fun onFailure(call: Call<RegistNotice>, t: Throwable) {
                println("실패")
                Log.d("Test", t.toString())
            }

            override fun onResponse(call: Call<RegistNotice>, response: Response<RegistNotice>) {
                if (response.body() != null) {
                    println("성공")
                }
            }
        })



        val call: Call<NoticeToken> =
            service.getNoticeToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjM3LCJyb2xlIjowLCJpYXQiOjE2MjkzNTI0NzUsImV4cCI6MTYyOTM1NjA3NSwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.w78k_zbpqx14VwruONvOvbh3cmM_qZy35dZvu1cNXlI")
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
}

