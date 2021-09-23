package com.example.crc_android.view.viewmore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.data.RetrofitHelper
import com.example.crc_android.databinding.FragmentNoticeregistBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.viewmodel.login.LoginViewModel
import com.example.crc_android.viewmodel.viewmore.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class NoticeregistFragment : UtilityBase.BaseFragment<FragmentNoticeregistBinding>(R.layout.fragment_noticeregist) {
    private val mainViewModel: MainViewModel by viewModels()
    private val loginViewModel : LoginViewModel by viewModels()
    private val TAG = "NoticeregistActivity"

    override fun FragmentNoticeregistBinding.onCreateView(){

        binding.returnnotice.setOnClickListener {
            findNavController().navigate(R.id.action_noticeregistFragment_to_adminnoticeFragment)
        }

        mainViewModel.response.observe(requireActivity(), {
            if(it.success) {
                binding.puttitle.text.clear()
                binding.putcontent.text.clear()
                findNavController().navigate(R.id.action_noticeregistFragment_to_adminnoticeFragment)
            }
        })

        binding.update.setOnClickListener {
            addData()
        }

    }

    private fun addData() {
        val title = binding.puttitle.text.toString()
        val content = binding.putcontent.text.toString()
        Log.d(TAG, "addData: 테스트 $title $content")
        if ((title.isBlank() || content.isBlank())) {
            Toast.makeText(requireContext(), "문자를 입력해주세요", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "addData: $title")
        } else {

            loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner) {
                var blog = RegistNotice(title, content)
                mainViewModel.postNotice(AES256.aesDecode(it.token).toString(), blog)
            }


        }
    }
}