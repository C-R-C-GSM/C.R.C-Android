package com.example.crc_android.view.suggest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentNoticecontentBinding
import com.example.crc_android.databinding.FragmentSuggestioncontentBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.util.App
import com.example.crc_android.viewmodel.admin.AdminViewModel
import com.example.crc_android.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class SuggestioncontentFragment: UtilityBase.BaseFragment<FragmentSuggestioncontentBinding>(R.layout.fragment_suggestioncontent) {

    val adminViewModel:AdminViewModel by viewModels()
    val loginViewModel:LoginViewModel by viewModels()


    override fun FragmentSuggestioncontentBinding.onCreateView(){
        binding.suggesttitle.setText(App.title)
        binding.suggestdate.setText(App.date)
        binding.suggestcontent.setText(App.content)
        binding.suggestnickname.setText(App.nickname)
        binding.suggestcomment.setText(App.reply)

        binding.leftarrow.setOnClickListener {
            findNavController().navigate(R.id.action_suggestcontentFragment_to_suggestionFragment)
        }
        binding.finish.setOnClickListener {
            findNavController().navigate(R.id.action_suggestcontentFragment_to_suggestionFragment)
        }


        binding.suggestcomment.setOnClickListener {
            loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner) {
                observeAdminRole(AES256.aesDecode(it.token).toString())

            }
            adminViewModel.adminSuccess.observe(viewLifecycleOwner) {
                if (it)
                    findNavController().navigate(R.id.action_suggestcontentFragment_to_suggestcommentFragment)
                else
                    Toast.makeText(requireContext(), "권한이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }




    }

    private fun observeAdminRole(token: String) = lifecycleScope.launch {
        adminViewModel.getCheckRole(token)
    }

    override fun onResume() {
        super.onResume()

    }


}

