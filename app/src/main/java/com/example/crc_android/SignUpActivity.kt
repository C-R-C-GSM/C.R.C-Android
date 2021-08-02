package com.example.crc_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        val signUpViewModel: SignUpViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SignUpViewModel::class.java)

        signUpViewModel.flag.observe(this, Observer {
            Log.d("로그", "라이브데이터 flag : $it")
            switchFragment(it, signUpViewModel)
        })


        val nextBtn = findViewById<Button>(R.id.signUpNextBtn)
        nextBtn.setOnClickListener {
            signUpViewModel.plusFlag()
        }


        //fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.sign_up_frame_layout, SignUpEmailFragment())
        transaction.commit()
    }


    private fun nextBtnClick() {

    }

    private fun switchFragment(flag: Int, signUpViewModel: SignUpViewModel) {
        if (flag in 0..3) {
            val transaction = supportFragmentManager.beginTransaction()

            when (flag) {
                0 -> {
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.right_in)
                        .add(R.id.sign_up_frame_layout, SignUpEmailFragment())
                }
                1 -> {
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out)
                        .add(R.id.sign_up_frame_layout, SignUpPasswordFragment())
                }
                2 -> {
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out)
                        .add(R.id.sign_up_frame_layout, SignUpNameFragment())
                }
                3 -> {
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out)
                        .add(R.id.sign_up_frame_layout, SignUpClassFragment())
                }
            }
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}