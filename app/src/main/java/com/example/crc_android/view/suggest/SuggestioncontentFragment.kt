package com.example.crc_android.view.suggest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RetrofitHelper
import com.example.crc_android.data.network.api.AdminNoticeApi
import com.example.crc_android.databinding.FragmentSuggestioncontentBinding
import com.example.crc_android.util.App
import com.example.crc_android.view.viewmore.NoticeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SuggestioncontentFragment : Fragment() {
    private lateinit var binding: FragmentSuggestioncontentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_suggestioncontent, container, false)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_suggestioncontent)
        binding.suggestcontent.setText(App.content)
        binding.comment.setText(App.content)
        setRetrofit()

        (requireActivity() as MainActivity)
        binding.leftarrow.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, NoticeFragment()).commit()

        }

        binding.finish.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, NoticeFragment()).commit()
        }

        return view
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