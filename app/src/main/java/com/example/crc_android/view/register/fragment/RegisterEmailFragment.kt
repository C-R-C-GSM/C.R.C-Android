package com.example.crc_android.view.register.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentSignUpEmailBinding
import com.example.crc_android.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterEmailFragment :
    UtilityBase.BaseFragment<FragmentSignUpEmailBinding>(R.layout.fragment_sign_up_email) {
    private val registerViewModel by activityViewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_email, container, false)
        binding.fragment = this
        observeViewModel()
        return binding.root
    }

    fun nextBtnClick(view: View) {
        registerViewModel.setEmail(binding.emailEdittext.text.toString())
    }

    private fun observeViewModel(){
        registerViewModel.errorMessage.observe(requireActivity(), Observer {
            when(it){
                "plz input email" -> Toast.makeText(requireContext(), "이메일을 입력해 주세요", Toast.LENGTH_SHORT).show()
                "plz input gsm email" -> Toast.makeText(requireContext(),"@gsm.hs.kr 학교 이메일을 사용해주세요",Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun backBtnClick(view: View) {
        registerViewModel.setFirstBackBtn()
    }

}