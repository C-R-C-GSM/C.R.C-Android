package com.example.crc_android.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.ActivityLoginBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.util.App
import com.example.crc_android.view.register.RegisterActivity
import com.example.crc_android.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        loginViewModel.loginApiCall(
            binding.loginEmail.text.toString(),
            binding.loginPassword.text.toString()
        )
    }

    private fun observeViewModel() {
        loginViewModel.loginResponse.observe(this, Observer {

            when (it.body()?.message) {
                "Issue Token and Login Success" -> {
                    Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()

                    Log.d("로그", "인코딩전 토근  : ${it.body()?.Token}")
                    //인코딩 후 datastore에 저장
                    val encodeToken = it.body()!!.Token?.let { it1 -> AES256.aesEncode(it1) }
                    setDataStore(encodeToken)
                    //RetrofitObject.TOKEN = it.body()?.Token?.let { it1 -> AES256.aesEncode(it1).toString() }.toString()
                    successLogin()
                }
                "no auth login try" -> Toast.makeText(this, "이메일을 확인하여 수락해 주세요", Toast.LENGTH_SHORT)
                    .show()
                "cannot connect db" -> Toast.makeText(this, "DB에 연결할 수 없습니다", Toast.LENGTH_SHORT)
                    .show()
                "cannot find this email" -> Toast.makeText(
                    this,
                    "이 이메일을 찾을 수 없습니다",
                    Toast.LENGTH_SHORT
                ).show()
                "wrong password" -> Toast.makeText(this, "잘못된 비밀번호 입니다", Toast.LENGTH_SHORT).show()
                "token error" -> Toast.makeText(this, "토큰 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "알수없는 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
            }
            /*    RetrofitObject.TOKEN = AES256.aesDecode(RetrofitObject.TOKEN).toString()
                Log.d("로그", "디코딩 후 : ${RetrofitObject.TOKEN}")*/
        })

        loginViewModel.errorMessage.observe(this, Observer {
            when (it) {
                "empty" -> Toast.makeText(this, "필수항목을 입력해 주세요", Toast.LENGTH_SHORT).show()
                "call error" -> Toast.makeText(this, "네트워크를 확인해 주세요", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setDataStore(token : String?){
        CoroutineScope(Dispatchers.Main).launch {
            if (token != null) {
                App.getInstance().getDataStore().setToken(token)
            }
        }

    }

    private fun getDataStore(){
        CoroutineScope(Dispatchers.Main).launch {
            App.getInstance().getDataStore().text.collect { it->
                Log.d("로그", "저장된 데이터스토어 토큰  : $it")
                val response = AES256.aesDecode(it)
                Log.d("로그", "저장된 데이터스토어 토큰 난독화 해제(디코딩) 후  : $response")
            }
        }

    }

    private fun successLogin() {
        //로그인 성공후 행동
        getDataStore()
    }
}