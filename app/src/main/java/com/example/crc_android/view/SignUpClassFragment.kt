package com.example.crc_android.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentSignUpClassBinding
import com.example.crc_android.model.RegisterAPI
import com.example.crc_android.model.RetrofitClient
import com.example.crc_android.model.RetrofitObject

import retrofit2.Response
import retrofit2.Retrofit


class SignUpClassFragment :
    UtilityBase.BaseFragment<FragmentSignUpClassBinding>(R.layout.fragment_sign_up_class) {

    private lateinit var retService: RegisterAPI

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

        binding.signUpNextBtn.setOnClickListener {
            nextBtnClick()
        }
        return binding.root
    }

     fun nextBtnClick() {
        if (TextUtils.isEmpty(binding.classEdittext.text.toString()))
            Toast.makeText(requireContext(), "학년, 반, 번호를 입력해 주세요", Toast.LENGTH_SHORT).show()
        else {
            SignUpActivity.signUpViewModel.setClassNumber(binding.classEdittext.text.toString())

            SignUpActivity.signUpViewModel.registerApiCall()

            SignUpActivity.signUpViewModel.registerResponse.observe(requireActivity(), Observer {
                Log.d("로그","회원가입 성공 로그 : $it")
            })
        }
    }



}