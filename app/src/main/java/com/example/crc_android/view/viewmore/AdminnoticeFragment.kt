package com.example.crc_android.view.viewmore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.adapter.RegisterMyAdapter
import com.example.crc_android.data.network.api.AdminNoticeApi
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RetrofitHelper
import com.example.crc_android.databinding.FragmentAdminnoticeBinding
import com.example.crc_android.viewmodel.viewmore.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AdminnoticeFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentAdminnoticeBinding
    lateinit var adapter: RegisterMyAdapter

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_adminnotice, container, false)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_adminnotice)
        binding.admin = this
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.recyclerviewAdminmain.adapter?.notifyDataSetChanged()
        setRetrofit()
        initialiseAdapter()

        (requireActivity() as MainActivity)
        binding.pluscontentBtn.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.navHostFragment, NoticeregistFragment()).commit()
        }


        binding.backfragment.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.navHostFragment, ViewmoreFragment()).commit()
        }




        return view
    }


    private fun initialiseAdapter() {
        binding.recyclerviewAdminmain.layoutManager = LinearLayoutManager(requireActivity())
        observeData()
        viewModel.get()
        binding.recyclerviewAdminmain.adapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        viewModel.get()
    }

    fun observeData() {
        viewModel.lst.observe(requireActivity(), Observer {
            Log.i("data", it.toString())
            binding.recyclerviewAdminmain.adapter =
                RegisterMyAdapter(viewModel, it, requireActivity())
        })
    }

    private fun setRetrofit() {
        val retrofit = RetrofitHelper.getInstance()

        val service = retrofit.create(AdminNoticeApi::class.java)
        val call: Call<NoticeToken> =
            service.getadminoticetoken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjM3LCJyb2xlIjowLCJpYXQiOjE2MjkzNTI0NzUsImV4cCI6MTYyOTM1NjA3NSwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.w78k_zbpqx14VwruONvOvbh3cmM_qZy35dZvu1cNXlI")
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


