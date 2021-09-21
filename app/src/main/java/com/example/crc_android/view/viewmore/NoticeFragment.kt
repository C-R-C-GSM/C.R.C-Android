package com.example.crc_android.view.viewmore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.adapter.MyAdapter
import com.example.crc_android.data.Data
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.databinding.FragmentViewmoreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NoticeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notice, container, false)
        viewManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, true)
        viewAdapter = MyAdapter(requireActivity(), Data.dataList)


        view.findViewById<Button>(R.id.backarrow).setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.navHostFragment, ViewmoreFragment()).commit()
        }

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_main).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter



        }
        return view
    }


    private fun setRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-3-34-189-53.ap-northeast-2.compute.amazonaws.com:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NOTICE::class.java)
        val call: Call<NoticeToken> =
            service.getnoticetoken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjM3LCJyb2xlIjowLCJpYXQiOjE2Mjk4NDY4NTQsImV4cCI6MTYyOTg1MDQ1NCwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.NsrgLDyvtiAuKeTvxTx3r7X9A4i94afveic2iBiXLMg")
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
}
