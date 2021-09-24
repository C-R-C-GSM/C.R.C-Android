package com.example.crc_android.view.viewmore

import androidx.navigation.fragment.findNavController
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentViewmoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewmoreFragment : UtilityBase.BaseFragment<FragmentViewmoreBinding>(R.layout.fragment_viewmore) {


    override fun FragmentViewmoreBinding.onCreateView() {
        binding.noticeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_viewmoreFragment_to_adminnoticeFragment)

        }
    }


}