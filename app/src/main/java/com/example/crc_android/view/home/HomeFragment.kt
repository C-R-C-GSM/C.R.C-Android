package com.example.crc_android.view.home

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : UtilityBase.BaseActivity<FragmentHomeBinding>(R.layout.fragment_home) {


    private val viewModel: HomeViewModel by viewModels()
    override fun FragmentHomeBinding.onCreate() {
        observeTotalFriend()
    }

    private fun observeTotalFriend() {
        viewModel.getTotalNumber("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjMwLCJyb2xlIjowLCJpYXQiOjE2MzEzMzI1NDcsImV4cCI6MTYzMTMzNjE0NywiaXNzIjoiQy5SLkNfU0VSVkVSIn0.6X4go-Ibr3bw26aQb4sYAelyYqEtpr4x4eiJ7vplMlI")
        viewModel.enterNumber.observe(this, {
            binding.enterNumberText.text = it
        })
        viewModel.noEntryNumber.observe(this, {
            binding.noEnterNumberText.text = it
        })
    }
}