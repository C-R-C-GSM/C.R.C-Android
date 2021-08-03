package com.example.crc_android

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentSignUpClassBinding


class SignUpClassFragment : UtilityBase.BaseFragment<FragmentSignUpClassBinding>(R.layout.fragment_sign_up_class) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up_class,container,false)
        binding.fragment = this

        return binding.root
    }

    fun nextBtnClick(view: View){
        if (TextUtils.isEmpty(binding.classEdittext.text))
            Toast.makeText(requireContext(),"학년, 반, 번호를 입력해 주세요",Toast.LENGTH_SHORT).show()
        else{
            //시작하는 코드
        }
    }

}