package com.example.crc_android.view.home

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentHomeBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.viewmodel.home.HomeViewModel
import com.example.crc_android.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : UtilityBase.BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val loginViewModel by viewModels<LoginViewModel>()

    private val viewModel: HomeViewModel by viewModels()
    override fun FragmentHomeBinding.onCreateView() {
        observeToken()
        observeTotalFriend()
        refreshAdapter()

    }

    override fun FragmentHomeBinding.onViewCreated() {
    }




    private fun observeTotalFriend() {

        viewModel.enterNumber.observe(viewLifecycleOwner, {
            binding.enterNumberText.text = it
        })
        viewModel.noEntryNumber.observe(viewLifecycleOwner, {
            binding.noEnterNumberText.text = it
        })
    }


    private fun observeToken() {
        loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner) {
            viewModel.getTotalNumber(AES256.aesDecode(it.token).toString())

        }
    }

    private fun refreshAdapter(){
        binding.refreshImg.setOnClickListener{
            observeToken()
        }
    }
}