package com.example.crc_android.view.viewmore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.adapter.RegisterMyAdapter
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.data.Data
import com.example.crc_android.data.network.api.AdminNoticeApi
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RetrofitHelper
import com.example.crc_android.databinding.FragmentAdminnoticeBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.viewmodel.login.LoginViewModel
import com.example.crc_android.viewmodel.viewmore.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint

class AdminnoticeFragment :  UtilityBase.BaseFragment<FragmentAdminnoticeBinding>(R.layout.fragment_adminnotice) {
    private val mainViewModel: MainViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    lateinit var adapter: RegisterMyAdapter

    override fun FragmentAdminnoticeBinding.onCreateView(

    ){
        adapter = RegisterMyAdapter(mainViewModel, Data.dataList, this@AdminnoticeFragment)
        binding.recyclerviewAdminmain.adapter = adapter
        initialiseAdapter()

        (requireActivity() as MainActivity)
        binding.pluscontentBtn.setOnClickListener {
            findNavController().navigate(R.id.action_adminnoticeFragment_to_noticeregistFragment)
        }


        binding.backfragment.setOnClickListener {
            findNavController().navigate(R.id.action_adminnoticeFragment_to_viewmoreFragment)
        }


    }


    private fun initialiseAdapter() {
        observeData()
        loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner) {
            mainViewModel.getNotice(AES256.aesDecode(it.token).toString())
        }
        adapter.notifyDataSetChanged()
    }

    fun observeData() {
        mainViewModel.dataList.observe(requireActivity(), Observer {
            Log.i("data", it.toString())
            if(it.notice_list != null) {
                Data.dataList = it.notice_list
                binding.recyclerviewAdminmain.adapter = RegisterMyAdapter(mainViewModel, Data.dataList, this)
            }

        })
    }
}