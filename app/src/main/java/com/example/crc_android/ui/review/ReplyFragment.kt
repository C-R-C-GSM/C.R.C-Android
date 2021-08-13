package com.example.crc_android.ui.review

import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentReplyBinding

class ReplyFragment : UtilityBase.BaseFragment<FragmentReplyBinding>(R.layout.fragment_reply) {

    override fun FragmentReplyBinding.onCreateView() {
        dataPassing(binding.replyEditText.text.toString())
        previousUi()

    }


    override fun FragmentReplyBinding.onViewCreated() {
    }

    fun dataPassing(data: String) {
        binding.finishTextBtn.setOnClickListener {
            val action =
                ReplyFragmentDirections.actionReplyFragmentToReviewFragment(
                    data
                )

            it.findNavController().navigate(action)
        }
    }


    private fun previousUi(){
        binding.previousImg.setOnClickListener{
            findNavController().navigate(R.id.action_replyFragment_to_reviewFragment)
        }
    }
}

