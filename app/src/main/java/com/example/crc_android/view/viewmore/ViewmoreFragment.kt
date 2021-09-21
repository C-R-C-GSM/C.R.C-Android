package com.example.crc_android.view.viewmore

import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentViewmoreBinding


class ViewmoreFragment : UtilityBase.BaseFragment<FragmentViewmoreBinding>(R.layout.fragment_viewmore) {


    override fun FragmentViewmoreBinding.onCreateView() {
        binding.noticeBtn.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, AdminnoticeFragment()).commit()
        }
    }


}