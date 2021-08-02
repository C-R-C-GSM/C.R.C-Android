package com.example.crc_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SignUpActivity : AppCompatActivity() {

    private var flag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        //fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.sign_up_frame_layout, SignUpEmailFragment())
        transaction.commit()
    }

    fun nextBtnClick(view: View) {
        switchFragment()
    }

    private fun switchFragment() {
        if (flag in 0..3) {
            val transaction = supportFragmentManager.beginTransaction()

            when (flag) {
                0 -> {
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out)
                        .replace(R.id.sign_up_frame_layout, SignUpEmailFragment())
                    flag++
                }
                1 -> {
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out)
                        .add(R.id.sign_up_frame_layout, SignUpPasswordFragment())
                    flag++
                }
                2 -> {
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out)
                        .add(R.id.sign_up_frame_layout, SignUpNameFragment())
                    flag++
                }
                3 -> {
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out)
                        .add(R.id.sign_up_frame_layout, SignUpClassFragment())
                    flag++
                }
            }
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}