package com.example.crc_android.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.ActivityLoginBinding
import com.example.crc_android.model.RetrofitObject
import com.example.crc_android.util.AES256
import com.example.crc_android.viewmodel.LoginViewModel
import com.example.crc_android.viewmodel.SignUpViewModel

class LoginActivity : UtilityBase.BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.activity = this

        loginViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(LoginViewModel::class.java)

    }

    fun createAuthBtnClick(view: View){
        val intent = Intent(this,SignUpActivity::class.java)
        startActivity(intent)
    }

    fun loginBtnClick(view: View) {
        if (TextUtils.isEmpty(binding.loginEmail.text.toString()))
            Toast.makeText(this, "이메일을 입력해 주세요", Toast.LENGTH_SHORT).show()
        else if (TextUtils.isEmpty(binding.loginPassword.text.toString()))
            Toast.makeText(this, "이메일을 입력해 주세요", Toast.LENGTH_SHORT).show()
        else {
            loginViewModel.apply {
                setEmail(binding.loginEmail.text.toString())
                setPassword(binding.loginPassword.text.toString())
                loginApiCall()
                loginResponse.observe(this@LoginActivity, Observer {
                    successApiCall(it.body()?.message,it.body()?.Token)
                })
            }

        }
    }

    //api가 호출된후 message값에 맞게 행동
    private fun successApiCall(message : String?, token : String?){
        Toast.makeText(this,"$message, $token",Toast.LENGTH_SHORT).show()
        if(message == "Issue Token and Login Success"){
            Toast.makeText(this,"로그인에 성공했습니다",Toast.LENGTH_SHORT).show()
            if (token != null) {
                RetrofitObject.TOKEN = AES256.aesEncode(token).toString()
                Log.d("로그","인코딩 후 : ${RetrofitObject.TOKEN}")
                RetrofitObject.TOKEN = AES256.aesDecode(RetrofitObject.TOKEN).toString()
                Log.d("로그","디코딩 후 : ${RetrofitObject.TOKEN}")
            }
            successLogin()
        }else if (message == "cannot connect db"){
            Toast.makeText(this,"DB에 연결할 수 없습니다",Toast.LENGTH_SHORT).show()
        }else if (message == "cannot find this email"){
            Toast.makeText(this,"이 이메일을 찾을 수 없습니다",Toast.LENGTH_SHORT).show()
        }else if (message == "wrong password"){
            Toast.makeText(this,"잘못된 비밀번호 입니다",Toast.LENGTH_SHORT).show()
        }else if (message == "token error"){
            Toast.makeText(this,"토큰 오류가 발생했습니다",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"알수없는 오류가 발생했습니다",Toast.LENGTH_SHORT).show()
        }
    }

    private fun successLogin() {
        //로그인 성공후 행동
    }
}