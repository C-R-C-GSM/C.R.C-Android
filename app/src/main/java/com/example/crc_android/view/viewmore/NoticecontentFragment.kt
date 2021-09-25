package com.example.crc_android.view.viewmore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.data.network.api.AdminNoticeApi
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RetrofitHelper
import com.example.crc_android.databinding.FragmentNoticecontentBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.util.App
import com.example.crc_android.viewmodel.admin.AdminViewModel
import com.example.crc_android.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint

class NoticecontentFragment : UtilityBase.BaseFragment<FragmentNoticecontentBinding>(R.layout.fragment_noticecontent) {

    private val loginViewModel:LoginViewModel by viewModels()
    private val adminViewModel:AdminViewModel by viewModels()
     var isAdmin= false

    override fun FragmentNoticecontentBinding.onCreateView(){
        binding.gettitle.setText(App.title)
        binding.getdate.setText(App.date)
        binding.getcontent.setText(App.content)

        loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner){
            checkRole(AES256.aesDecode(it.token).toString())
        }

        adminViewModel.adminSuccess.observe(requireActivity()){
            isAdmin = it
        }

        binding.returnbtn.setOnClickListener {
            if(isAdmin) {
                findNavController().navigate(R.id.action_noticecontentFragment_to_adminnoticeFragment)
            } else {
                findNavController().navigate(R.id.action_noticecontentFragment_to_noticeFragment)
            }        }
        binding.finishbtn.setOnClickListener {
            if(isAdmin) {
                findNavController().navigate(R.id.action_noticecontentFragment_to_adminnoticeFragment)
            } else {
                findNavController().navigate(R.id.action_noticecontentFragment_to_noticeFragment)
            }        }
    }
    private fun checkRole(token : String){
        lifecycleScope.launch {
            adminViewModel.getCheckRole(token)
        }
    }
}




