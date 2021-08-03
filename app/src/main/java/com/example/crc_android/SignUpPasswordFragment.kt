package com.example.crc_android

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
                Log.d(
                    "로그",
                    "1 : ${binding.passwordEdittext.text}, 2: ${binding.passwordCheckEdittext.text}"
                )
                val password = binding.passwordEdittext.text
                val passwordCheck = binding.passwordCheckEdittext.text
                if (password == passwordCheck) {
                    Log.d("로그", "같다")
                    SignUpActivity.signUpViewModel.plusFlag()
                } else {
                    Log.d("로그", "안같다")
                    Toast.makeText(requireContext(), "비밀번호와 비밀번호 확인이 다릅니다", Toast.LENGTH_SHORT)
                        .show()
                }

            }

    }

}