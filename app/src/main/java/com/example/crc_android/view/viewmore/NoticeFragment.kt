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
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.adapter.MyAdapter
import com.example.crc_android.adapter.RegisterMyAdapter
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.data.Data
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.databinding.FragmentNoticeBinding
import com.example.crc_android.databinding.FragmentViewmoreBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.viewmodel.login.LoginViewModel
import com.example.crc_android.viewmodel.viewmore.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class NoticeFragment : UtilityBase.BaseFragment<FragmentNoticeBinding>(R.layout.fragment_notice) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val mainViewModel: MainViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun FragmentNoticeBinding.onCreateView(

    ) {
        viewAdapter = MyAdapter(this@NoticeFragment, Data.dataList)

        mainViewModel.dataList.observe(requireActivity(),){
            if(it.notice_list != null) {
                Data.dataList = it.notice_list
                binding.recyclerviewMain.adapter = MyAdapter(this@NoticeFragment, Data.dataList)
            }
        }

        loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner){
            mainViewModel.getNotice(AES256.aesDecode(it.token).toString())
        }


        binding.backarrow.setOnClickListener {
            findNavController().navigate(R.id.action_noticeFragment_to_viewmoreFragment)
        }

        recyclerView = binding.recyclerviewMain.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }
    }



}
