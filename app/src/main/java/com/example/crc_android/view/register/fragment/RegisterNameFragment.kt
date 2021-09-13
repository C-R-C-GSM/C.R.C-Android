package com.example.crc_android.view.register.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentSignUpNameBinding
import com.example.crc_android.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterNameFragment :
    UtilityBase.BaseFragment<FragmentSignUpNameBinding>(R.layout.fragment_sign_up_name) {
    private val registerViewModel by activityViewModels<RegisterViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_name, container, false)
        binding.fragment = this
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        registerViewModel.errorMessage.observe(requireActivity(), Observer {
            when (it) {
                "plz input name" -> Toast.makeText(
                    requireContext(),
                    "이름을 입력해 주세요",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }

    fun nextBtnClick(view: View) {
        registerViewModel.setName(binding.nameEdittext.text.toString())
    }

    fun backBtnClick(view: View) {
        registerViewModel.minusFlag()
    }

}
