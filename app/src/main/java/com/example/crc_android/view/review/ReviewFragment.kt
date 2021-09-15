package com.example.crc_android.view.review

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crc_android.R
import com.example.crc_android.adapter.ReviewCheckAdapter
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentReviewBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.viewmodel.admin.AdminViewModel
import com.example.crc_android.viewmodel.login.LoginViewModel
import com.example.crc_android.viewmodel.review.ReviewViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ReviewFragment : UtilityBase.BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {

    private val reviewViewModel: ReviewViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val adminViewModel: AdminViewModel by viewModels()
    private val reviewAdapter: ReviewCheckAdapter by lazy {
        ReviewCheckAdapter()
    }


    override fun FragmentReviewBinding.onCreateView() {
        observeToken()
        adapterItemOnClick()
        setAdapter()

        observeReviewCheck()
        nextMovePage()

    }

    override fun FragmentReviewBinding.onViewCreated() {
    }

    private fun setAdapter() {
        binding.reviewCheckRecycler.apply {
            this.adapter = reviewAdapter
            this.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.setHasFixedSize(false)
        }
    }

    private fun observeToken() {

        loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner) {
            reviewViewModel.getReviewCheck(AES256.aesDecode(it.token).toString())
            observeAdminRole(AES256.aesDecode(it.token).toString())

        }
    }

    private fun observeReviewCheck() = lifecycleScope.launch {

        reviewViewModel.reviewItem.observe(viewLifecycleOwner, { data ->

            reviewAdapter.setItemList(data)


        })

    }

    private fun observeAdminRole(token: String) = lifecycleScope.launch {
        adminViewModel.getCheckRole(token)
    }

    private fun adapterItemOnClick() {
        reviewAdapter.setOnItemClickListener(object : ReviewCheckAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: String) {

                adminViewModel.adminSuccess.observe(viewLifecycleOwner) {
                    Log.d("TAG", "onItemClick: $it")
                    if (it)
                        findNavController().navigate(R.id.action_reviewFragment_to_replyFragment)
                    else
                        Toast.makeText(requireContext(), "권환이 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }


        })
    }

    private fun nextMovePage() {
        binding.nextImage.setOnClickListener {
            findNavController().navigate(R.id.action_reviewFragment_to_reviewRegisterFragment2)
        }
    }

}

