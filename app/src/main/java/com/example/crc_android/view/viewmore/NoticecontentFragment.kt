package com.example.crc_android.view.viewmore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.data.network.api.AdminNoticeApi
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RetrofitHelper
import com.example.crc_android.databinding.FragmentNoticecontentBinding
import com.example.crc_android.util.App
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint

class NoticecontentFragment : UtilityBase.BaseFragment<FragmentNoticecontentBinding>(R.layout.fragment_noticecontent) {

    override fun FragmentNoticecontentBinding.onCreateView(){
        binding.gettitle.setText(App.title)
        binding.getdate.setText(App.date)
        binding.getcontent.setText(App.content)

        binding.returnbtn.setOnClickListener {
            findNavController().navigate(R.id.action_noticecontentFragment_to_adminnoticeFragment)
        }
        binding.finishbtn.setOnClickListener {
            findNavController().navigate(R.id.action_noticecontentFragment_to_adminnoticeFragment)
        }
    }
}




