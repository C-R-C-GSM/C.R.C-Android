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
import com.example.crc_android.data.network.model.suggest.CommentData
import com.example.crc_android.databinding.FragmentRegistractionBinding
import com.example.crc_android.viewmodel.suggest.RegistractionViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class SuggestioncommentFragment : Fragment() {

    lateinit var binding: FragmentRegistractionBinding
    private var viewManager = LinearLayoutManager(requireActivity())
    private lateinit var viewModel: RegistractionViewModel
    private val TAG = "RegistractionActivity"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_suggestioncomment, container, false)
        binding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_suggestioncomment)
        viewModel = ViewModelProvider(requireActivity()).get(RegistractionViewModel::class.java)


        setRetrofit()

        (requireActivity() as MainActivity)
        binding.leftarrow.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, SuggestionFragment()).commit()
        }

        binding.update.setOnClickListener {
            addData()
        }
        return view

    }

    private fun addData() {
        val comment = binding.content.text.toString()
        Log.d(TAG, "addData: 테스트 $comment ")
        if (comment.isBlank()){
            Toast.makeText(requireContext(), "댓글을 적어주세요", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "addData: $comment")
        } else {
            var blog = CommentData(comment)
            viewModel.add(blog)
            binding.nickname.text.clear()
            binding.title.text.clear()
            binding.content.text.clear()
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, SuggestionFragment()).commit()
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

