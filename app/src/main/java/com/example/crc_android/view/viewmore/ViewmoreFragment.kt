package com.example.crc_android.view.viewmore

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentViewmoreBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.view.login.LoginActivity
import com.example.crc_android.view.login.LoginActivity_GeneratedInjector
import com.example.crc_android.viewmodel.admin.AdminViewModel
import com.example.crc_android.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ViewmoreFragment : UtilityBase.BaseFragment<FragmentViewmoreBinding>(R.layout.fragment_viewmore) {

    private val loginViewModel : LoginViewModel by viewModels()
    private val adminViewModel: AdminViewModel by viewModels()

    override fun FragmentViewmoreBinding.onCreateView() {
        binding.noticeBtn.setOnClickListener {
            loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner){
                checkRole(AES256.aesDecode(it.token).toString())
            }
            adminViewModel.adminSuccess.observe(requireActivity(),{
                if(it){
                    findNavController().navigate(R.id.action_viewmoreFragment_to_adminnoticeFragment)
                }else{
                    findNavController().navigate(R.id.action_viewmoreFragment_to_noticeFragment2)

                }
            })
        }

        binding.logoutButton.setOnClickListener {
            loginViewModel.saveEmail("")
            loginViewModel.savePassword("")
            loginViewModel.saveToken("")
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()

        }
    }
    private fun checkRole(token:String){
        lifecycleScope.launch {
            adminViewModel.getCheckRole(token)
        }
    }

}

