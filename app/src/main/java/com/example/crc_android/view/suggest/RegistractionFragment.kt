package com.example.crc_android.view.suggest

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
import com.example.crc_android.data.RetrofitHelper
import com.example.crc_android.data.network.model.suggest.RegistData
import com.example.crc_android.databinding.FragmentRegistractionBinding
import com.example.crc_android.view.viewmore.AdminnoticeFragment
import com.example.crc_android.viewmodel.viewmore.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class RegistractionFragment : Fragment() {

    lateinit var binding: FragmentRegistractionBinding
    private var viewManager = LinearLayoutManager(requireActivity())
    private lateinit var viewModel: MainViewModel
    private val TAG = "RegistractionActivity"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registraction, container, false)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_registraction)
        binding.activity = this
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


        setRetrofit()

        (requireActivity() as MainActivity)
        binding.leftarrow.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, AdminnoticeFragment()).commit()
        }

        binding.update.setOnClickListener {
            addData()
        }
        return view

    }

    private fun addData() {
        val nickname = binding.nickname.text.toString()
        val title = binding.title.text.toString()
        val content = binding.content.text.toString()
        Log.d(TAG, "addData: 테스트 $title $content")
        if ((title.isBlank() || content.isBlank())) {
            Toast.makeText(requireContext(), "문자가 없어용~~~", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "addData: $title")
        } else {
            var blog = RegistData(nickname, title, content)
            viewModel.add(blog)
            binding.nickname.text.clear()
            binding.title.text.clear()
            binding.content.text.clear()
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, AdminnoticeFragment()).commit()
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

