package com.example.crc_android.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentSignUpPasswordBinding

class SignUpPasswordFragment :
    UtilityBase.BaseFragment<FragmentSignUpPasswordBinding>(R.layout.fragment_sign_up_password) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_password, container, false)
        binding.fragment = this
        return binding.root
    }

    fun nextBtnClick(view: View) {
        if (TextUtils.isEmpty(binding.passwordEdittext.text))
            Toast.makeText(requireContext(), "비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show()
        else
            if (TextUtils.isEmpty(binding.passwordCheckEdittext.text)) {
                Toast.makeText(requireContext(), "비밀번호 확인을 입력해 주세요", Toast.LENGTH_SHORT).show()
            } else {

                if (binding.passwordEdittext.text.toString() == binding.passwordCheckEdittext.text.toString()) {
                    SignUpActivity.signUpViewModel.setPassword(binding.passwordCheckEdittext.text.toString())
                    SignUpActivity.signUpViewModel.plusFlag()
                } else {
                    Toast.makeText(requireContext(), "비밀번호와 비밀번호 확인이 다릅니다", Toast.LENGTH_SHORT)
                        .show()
                }

            }
    }

}