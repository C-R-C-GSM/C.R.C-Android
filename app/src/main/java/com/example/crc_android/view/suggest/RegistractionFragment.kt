package com.example.crc_android.view.suggest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.data.network.model.suggest.RegisterRequest
import com.example.crc_android.databinding.FragmentNoticeregistBinding
import com.example.crc_android.databinding.FragmentRegistractionBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.viewmodel.login.LoginViewModel
import com.example.crc_android.viewmodel.suggest.SuggestViewModel
import com.example.crc_android.viewmodel.viewmore.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class RegistractionFragment : UtilityBase.BaseFragment<FragmentRegistractionBinding>(R.layout.fragment_registraction) {
    private val suggestViewModel: SuggestViewModel by viewModels()
    private val loginViewModel : LoginViewModel by viewModels()
    private val TAG = "NoticeregistActivity"

    override fun FragmentRegistractionBinding.onCreateView(){

        binding.leftarrow.setOnClickListener {
            findNavController().navigate(R.id.action_registractionFragment_to_suggestionFragment)
        }

            suggestViewModel.response.observe(requireActivity(), {
            if(it.success) {
                findNavController().navigate(R.id.action_registractionFragment_to_suggestionFragment)
            }
        })

        binding.update.setOnClickListener {
            addData()
        }

    }

    private fun addData() {
        val title = binding.title.text.toString()
        val content = binding.content.text.toString()
        val nickname = binding.nickname.text.toString()
        Log.d(TAG, "addData: 테스트 $title $content")
        if ((title.isBlank() || content.isBlank())) {
            Toast.makeText(requireContext(), "문자를 입력해주세요", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "addData: $title")
        } else {

            loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner) {
                var blog = RegisterRequest(title, content,nickname)
                suggestViewModel.postregister(AES256.aesDecode(it.token).toString(), blog)
            }

        }
    }
}
