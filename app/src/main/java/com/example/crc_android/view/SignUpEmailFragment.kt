package com.example.crc_android.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.crc_android.R
import com.example.crc_android.view.SignUpActivity.Companion.signUpViewModel
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentSignUpEmailBinding


class SignUpEmailFragment :
    UtilityBase.BaseFragment<FragmentSignUpEmailBinding>(R.layout.fragment_sign_up_email) {


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



        return binding.root
    }

    fun nextBtnClick(view: View) {
        if (TextUtils.isEmpty(binding.emailEdittext.text.toString()))
            Toast.makeText(requireContext(), "이메일을 입력해 주세요", Toast.LENGTH_SHORT).show()
        else {
            signUpViewModel.setEmail(binding.emailEdittext.text.toString())
            signUpViewModel.plusFlag()
        }


    }

}