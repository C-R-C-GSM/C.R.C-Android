package com.example.crc_android.view.viewmore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.data.network.api.AdminNoticeApi
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RetrofitHelper
import com.example.crc_android.databinding.FragmentNoticecontentBinding
import com.example.crc_android.util.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NoticecontentFragment : Fragment() {
    private lateinit var binding: FragmentNoticecontentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_noticecontent, container, false)
        binding.gettitle.setText(App.title)
        binding.getdate.setText(App.date)
        binding.getcontent.setText(App.content)
        setRetrofit()

        (requireActivity() as MainActivity)
        binding.returnbtn.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction().addToBackStack(null)
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, AdminnoticeFragment()).commit()

        }

        binding.finishbtn.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction().addToBackStack(null)
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, AdminnoticeFragment()).commit()
        }

        return binding.root
    }


    private fun setRetrofit() {
        val retrofit = RetrofitHelper.getInstance()

        val service = retrofit.create(AdminNoticeApi::class.java)
        val call: Call<NoticeToken> = service.getadminoticetoken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjI3LCJyb2xlIjoxLCJpYXQiOjE2MzE3NjE0MzUsImV4cCI6MTYzMTc2NTAzNSwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.NNv0w7jCWE6Dzh61VzmtSGJ5eb0wKc4WlRwPqwtClrU")
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