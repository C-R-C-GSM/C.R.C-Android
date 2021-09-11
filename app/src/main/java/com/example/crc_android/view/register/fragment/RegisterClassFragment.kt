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
import com.example.crc_android.databinding.FragmentSignUpClassBinding
import com.example.crc_android.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterClassFragment :
    UtilityBase.BaseFragment<FragmentSignUpClassBinding>(R.layout.fragment_sign_up_class) {
    private val registerViewModel by activityViewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_class, container, false)
        binding.fragment = this
        observeViewModel()

        return binding.root
    }

    fun nextBtnClick(view: View) {
        registerViewModel.setClassNumber(binding.classEdittext.text.toString())

    }

    private fun observeViewModel() {
        registerViewModel.classNumber.observe(requireActivity(), Observer {
            if (it != "null") {
                binding.progressBar.visibility = View.VISIBLE
            }
        })

        registerViewModel.errorMessage.observe(requireActivity(), Observer {
            when (it) {
                "plz input classNumber" -> {
                    Toast.makeText(
                        requireContext(),
                        "학년, 반, 번호를 정확히 입력해 주세요",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.progressBar.visibility = View.GONE
                }
                "register sucess" -> Toast.makeText(
                    requireContext(),
                    "회원가입에 성공했습니다, 이메일을 확인해주세요!",
                    Toast.LENGTH_LONG
                ).show()
                "email already existed" -> Toast.makeText(
                    requireContext(),
                    "이미 존재하는 이메일 입니다",
                    Toast.LENGTH_SHORT
                ).show()
                "invalid email address" -> Toast.makeText(
                    requireContext(),
                    "잘못된 이메일 주소 입니다",
                    Toast.LENGTH_SHORT
                ).show()
                "idk error" -> Toast.makeText(
                    requireContext(),
                    "알수없는 오류가 발생했습니다",
                    Toast.LENGTH_SHORT
                ).show()

                "register fail" -> Toast.makeText(
                    requireContext(),
                    "회원가입에 실패했습니다",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }

    fun backBtnClick(view: View) {
        registerViewModel.minusFlag()
    }
}