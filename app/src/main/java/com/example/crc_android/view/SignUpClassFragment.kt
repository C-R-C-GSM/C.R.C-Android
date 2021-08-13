package com.example.crc_android.view

import android.content.Intent
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
import com.example.crc_android.databinding.FragmentSignUpClassBinding
import com.example.crc_android.model.RegisterAPI
import com.example.crc_android.model.RetrofitClient
import com.example.crc_android.model.RetrofitObject
import com.example.crc_android.view.SignUpActivity.Companion.signUpViewModel
import com.example.movie.base.UtilityBase

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
            signUpViewModel.setClassNumber(binding.classEdittext.text.toString())

            signUpViewModel.registerApiCall()

            signUpViewModel.registerResponse.observe(requireActivity(), Observer {
                successApiCall(it.body()?.message)
            })
        }
    }

    //api가 호출된후 message값에 맞게 행동
    private fun successApiCall(message : String?){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
        if(message == "register sucess"){
            Toast.makeText(requireContext(),"회원가입에 성공했습니다",Toast.LENGTH_SHORT).show()
            successSignUp()
        }else if (message == "email already existed"){
            Toast.makeText(requireContext(),"이미 존재하는 이메일 입니다",Toast.LENGTH_SHORT).show()
        }else if (message == "invalid email address"){
            Toast.makeText(requireContext(),"잘못된 이메일 주소 입니다",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(),"알수없는 오류가 발생했습니다",Toast.LENGTH_SHORT).show()
        }
    }

    fun backBtnClick(view: View){
        signUpViewModel.minusFlag()
    }

    private fun successSignUp(){
        //회원가입후 넘어갈 페이지 작성
    }



}