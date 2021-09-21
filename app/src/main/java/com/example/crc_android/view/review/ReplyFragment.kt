package com.example.crc_android.view.review

import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.data.network.model.ReviewReplyRequest
import com.example.crc_android.databinding.FragmentReplyBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.viewmodel.login.LoginViewModel
import com.example.crc_android.viewmodel.review.ReviewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReplyFragment : UtilityBase.BaseFragment<FragmentReplyBinding>(R.layout.fragment_reply) {

    override fun FragmentReplyBinding.onCreateView() {
        dataPassing(binding.replyEditText.text)
        previousUi()

    }

    private val loginViewModel: LoginViewModel by viewModels()

    private val viewModel by viewModels<ReviewViewModel>()
    private val args by navArgs<ReplyFragmentArgs>()


    override fun FragmentReplyBinding.onViewCreated() {
    }

    fun dataPassing(data: Editable) {
        binding.finishTextBtn.setOnClickListener {
            if (!TextUtils.isEmpty(data)) {

                observePostReply(data)
                finishBtn()
            } else {
                Toast.makeText(requireContext(), "답변을 입력해주세요", Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun previousUi() {
        binding.previousImg.setOnClickListener {
            findNavController().navigate(R.id.action_replyFragment_to_reviewFragment)
        }
    }

    private fun finishBtn() {

        findNavController().navigate(R.id.action_replyFragment_to_reviewFragment)
    }

    private fun observeToken(reviewReplyRequest: ReviewReplyRequest) {

        loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner) {
            viewModel.reviewReply(
                AES256.aesDecode(it.token).toString(),
                reviewReplyRequest
            )
        }
    }

    private fun observePostReply(data: Editable) {

        val reviewRequest = ReviewReplyRequest(
             data.toString(),args.reviewId
        )
        observeToken(reviewRequest)

    }
}


