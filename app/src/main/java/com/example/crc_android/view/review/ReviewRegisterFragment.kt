package com.example.crc_android.view.review

import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.data.network.model.ReviewPostRequest
import com.example.crc_android.data.network.model.ReviewReplyRequest
import com.example.crc_android.databinding.FragmentReviewRegisterBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.viewmodel.login.LoginViewModel
import com.example.crc_android.viewmodel.review.ReviewViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ReviewRegisterFragment :
    UtilityBase.BaseFragment<FragmentReviewRegisterBinding>(R.layout.fragment_review_register) {

    private val viewModel by viewModels<ReviewViewModel>()

    private val loginViewModel: LoginViewModel by viewModels()
    override fun FragmentReviewRegisterBinding.onCreateView() {
        previousView()
        textUtilTest()
        getDay()
        getPm()
    }

    override fun FragmentReviewRegisterBinding.onViewCreated() {
    }

    private fun textUtilTest() {

        binding.finishTextBtn.setOnClickListener {
            if (!TextUtils.isEmpty(binding.contentEdit.text) && !TextUtils.isEmpty(binding.nicknameEdit.text)) {

                requestPostRegister()

                findNavController().navigate(R.id.action_reviewRegisterFragment_to_reviewFragment)
            } else {
                Toast.makeText(requireContext(), "빈칸을 빠짐없이 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeToken(reviewPostRequest: ReviewPostRequest) {

        loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner) {
            viewModel.reviewRegister(
                AES256.aesDecode(it.token).toString(),
                reviewPostRequest
            )
        }
    }

    private fun previousView() {
        binding.previousImg.setOnClickListener {
            findNavController().navigate(R.id.action_reviewRegisterFragment_to_reviewFragment)
        }
    }


    private fun requestPostRegister() {

        val reviewPostRequest = ReviewPostRequest(
            binding.reviewStartRating.rating.toInt(),
            binding.contentEdit.text.toString(),
            binding.nicknameEdit.text.toString(),
            binding.dayText.text.toString(),  binding.dayPmFm.text.toString()
        )
        observeToken(reviewPostRequest)

    }

    private fun getDay() {
        val now: Long = System.currentTimeMillis()

        val toTimeStamp = Date(now)
        val date = SimpleDateFormat("MM월 dd일", Locale.forLanguageTag("ko"))
        binding.dayText.text = date.format(toTimeStamp)
    }

    private fun getPm(){
        val now: Long = System.currentTimeMillis()

        val toTimeStamp = Date(now)
        val date = SimpleDateFormat(" aa", Locale.forLanguageTag("ko"))
        binding.dayPmFm.text = date.format(toTimeStamp)
    }

}

