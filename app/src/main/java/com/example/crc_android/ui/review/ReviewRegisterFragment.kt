//package com.example.crc_android.ui.review.review
//
//import android.text.TextUtils
//import android.widget.Toast
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import com.example.crc_android.R
//import com.example.crc_android.base.UtilityBase
//import com.example.crc_android.data.network.model.ReviewPostRequest
//import com.example.crc_android.databinding.FragmentReviewRegisterBinding
//import com.example.crc_android.viewmodel.review.ReviewViewModel
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class ReviewRegisterFragment :
//    UtilityBase.BaseFragment<FragmentReviewRegisterBinding>(R.layout.fragment_review_register) {
//
//    private val viewModel by viewModels<ReviewViewModel>()
//    override fun FragmentReviewRegisterBinding.onCreateView() {
//        textUtilTest()
//    }
//
//    override fun FragmentReviewRegisterBinding.onViewCreated() {
//    }
//
//    private fun textUtilTest() {
//
//        binding.finishTextBtn.setOnClickListener {
//            if (!TextUtils.isEmpty(binding.contentEdit.text) && !TextUtils.isEmpty(binding.nicknameEdit.text)) {
//
//                requestPostRegister()
//
//                findNavController().navigate(R.id.action_reviewRegister_to_reviewFragment)
//            } else {
//                Toast.makeText(requireContext(), "빈칸을 빠짐없이 입력해주세요", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    fun requestPostRegister() {
//
//        val reviewPostRequest = ReviewPostRequest(
//            binding.reviewStartRating.rating.toInt(),
//            binding.contentEdit.text.toString(),
//            binding.nicknameEdit.text.toString(),
//            "", ""
//        )
//
//        viewModel.reviewRegister(
//            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjI1LCJyb2xlIjowLCJpYXQiOjE2MzEwNzcyNzksImV4cCI6MTYzMTA4MDg3OSwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.pY8Qd_1umboN0DFlkxHnn0xC3iDkx-D8vxIOhsJc-Wg",
//            reviewPostRequest
//        )
//    }
//}