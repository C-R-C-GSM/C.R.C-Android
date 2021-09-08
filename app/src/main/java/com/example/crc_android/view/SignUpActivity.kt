package com.example.crc_android.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.crc_android.*
import com.example.crc_android.databinding.ActivitySignUpBinding
import com.example.crc_android.viewmodel.SignUpViewModel
import com.example.movie.base.UtilityBase

class SignUpActivity : UtilityBase.BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {


    companion object {
        lateinit var signUpViewModel: SignUpViewModel
    }

    override fun onBackPressed() {
        Toast.makeText(this,"뒤로가기 아이콘을 클릭해 주세요",Toast.LENGTH_SHORT).show()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.activity = this

        signUpViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SignUpViewModel::class.java)


        //fragment 순번(email -> password -> name -> 반번호)
        signUpViewModel.flag.observe(this, Observer {
            Log.d("로그", "라이브데이터 flag : $it")
            if (it >=0){
                switchFragment(it)
            }else{
                Toast.makeText(this,"더이상 뒤로갈수 없습니다",Toast.LENGTH_SHORT).show()
            }
        })


    }


    private fun switchFragment(flag: Int) {
        if (flag in 0..3) {
            val transaction = supportFragmentManager.beginTransaction()

            when (flag) {
                0 -> {
                    transaction
                        .replace(R.id.sign_up_frame_layout, SignUpEmailFragment())

                }
                1 -> {
                    transaction
                        .replace(R.id.sign_up_frame_layout, SignUpPasswordFragment())

                }
                2 -> {
                    transaction
                        .replace(R.id.sign_up_frame_layout, SignUpNameFragment())

                }
                3 -> {
                    transaction
                        .replace(R.id.sign_up_frame_layout, SignUpClassFragment())

                }
            }
            transaction.addToBackStack(null)
            transaction.commit()

        }
    }
}