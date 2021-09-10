package com.example.crc_android.view.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.ActivityLoginBinding
import com.example.crc_android.model.RetrofitObject
import com.example.crc_android.util.AES256
import com.example.crc_android.view.register.RegisterActivity
import com.example.crc_android.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : UtilityBase.BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val loginViewModel by viewModels<LoginViewModel>()
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.activity = this
            observeViewModel()
    }

    fun createAuthBtnClick(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun loginBtnClick(view: View) {
        if (TextUtils.isEmpty(binding.loginEmail.text.toString()) || TextUtils.isEmpty(binding.loginPassword.text.toString()))
            Toast.makeText(this, "필수항목을 입력해 주세요", Toast.LENGTH_SHORT).show()
        else
            loginViewModel.loginApiCall(
                binding.loginEmail.text.toString(),
                binding.loginPassword.text.toString()
            )
    }

    private fun observeViewModel() {
        loginViewModel.loginResponse.observe(this, Observer {
            if (it.isSuccessful) {
                when (it.body()?.message) {
                    "Issue Token and Login Success" -> {
                        Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                        Log.d("로그", "인코딩 전 토큰 : ${it.body()!!.Token}")
                        RetrofitObject.TOKEN = it.body()?.Token?.let { it1 -> AES256.aesEncode(it1).toString() }.toString()
                        Log.d("로그", "인코딩 후 : ${RetrofitObject.TOKEN}")
                        successLogin()
                    }
                    "no auth login try" -> Toast.makeText(this, "이메일을 확인하여 수락해 주세요", Toast.LENGTH_SHORT).show()
                    "cannot connect db" -> Toast.makeText(this, "DB에 연결할 수 없습니다", Toast.LENGTH_SHORT).show()
                    "cannot find this email" -> Toast.makeText(this, "이 이메일을 찾을 수 없습니다", Toast.LENGTH_SHORT).show()
                    "wrong password" -> Toast.makeText(this, "잘못된 비밀번호 입니다", Toast.LENGTH_SHORT).show()
                    "token error" -> Toast.makeText(this, "토큰 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(this, "알수없는 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
                }

            /*    RetrofitObject.TOKEN = AES256.aesDecode(RetrofitObject.TOKEN).toString()
                Log.d("로그", "디코딩 후 : ${RetrofitObject.TOKEN}")*/
            }else Toast.makeText(this, "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show()
        })
    }

    private fun successLogin() {
        //로그인 성공후 행동
    }
}