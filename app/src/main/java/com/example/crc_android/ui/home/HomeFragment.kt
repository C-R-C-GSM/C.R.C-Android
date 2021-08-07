package com.example.crc_android.ui.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : UtilityBase.BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {


    private val viewModel: HomeViewModel by viewModels()
    override fun FragmentHomeBinding.onCreateView() {
        observeTotalFriend()

    }

    override fun FragmentHomeBinding.onViewCreated() {
    }

    private fun observeTotalFriend() {
        viewModel.getTotalNumber("")

    }
}