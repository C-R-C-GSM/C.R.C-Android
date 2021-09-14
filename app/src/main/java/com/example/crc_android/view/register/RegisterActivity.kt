package com.example.crc_android.view.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.crc_android.*
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.ActivitySignUpBinding
import com.example.crc_android.view.register.fragment.RegisterClassFragment
import com.example.crc_android.view.register.fragment.RegisterEmailFragment
import com.example.crc_android.view.register.fragment.RegisterNameFragment
import com.example.crc_android.view.register.fragment.RegisterPasswordFragment
import com.example.crc_android.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : UtilityBase.BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onBackPressed() {
        Toast.makeText(this,"뒤로가기 아이콘을 클릭해 주세요",Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.activity = this
        observeViewModel()
    }

    private fun observeViewModel(){
        //다음으로 가기 클릭시
        registerViewModel.flag.observe(this, Observer {
            switchFragment(it)
        })

        //회원가입 성공시
        registerViewModel.errorMessage.observe(this, Observer {
            if (it == "register sucess"){
                finish()
            }
        })

        //회원가입 가장 처음 이메일 부분에서 뒤로가기 클릭시
        registerViewModel.firstBackBtn.observe(this, Observer {
            finish()
        })
    }


    //fragment 순번(email -> password -> name -> 반번호)
    private fun switchFragment(flag: Int) {
        if (flag in 0..3) {
            val transaction = supportFragmentManager.beginTransaction()

            when (flag) {
                0 -> {
                    transaction
                        .replace(R.id.sign_up_frame_layout, RegisterEmailFragment())

                }
                1 -> {
                    transaction
                        .replace(R.id.sign_up_frame_layout, RegisterPasswordFragment())

                }
                2 -> {
                    transaction
                        .replace(R.id.sign_up_frame_layout, RegisterNameFragment())

                }
                3 -> {
                    transaction
                        .replace(R.id.sign_up_frame_layout, RegisterClassFragment())

                }
            }
            transaction.addToBackStack(null)
            transaction.commit()

        }
    }
}