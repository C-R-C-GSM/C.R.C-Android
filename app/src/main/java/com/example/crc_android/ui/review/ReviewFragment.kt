//package com.example.crc_android.ui.review.review
//
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.crc_android.R
//import com.example.crc_android.adapter.ReviewCheckAdapter
//import com.example.crc_android.base.UtilityBase
//import com.example.crc_android.viewmodel.review.ReviewViewModel
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.launch
//
//
//@AndroidEntryPoint
//class ReviewFragment : UtilityBase.BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {
//
//    private val reviewViewModel: ReviewViewModel by viewModels()
//    private val reviewAdapter: ReviewCheckAdapter by lazy {
//        ReviewCheckAdapter()
//    }
//
//    private val recyclerView: RecyclerView by lazy {
//        binding.reviewCheckRecycler
//    }
//
//    override fun FragmentReviewBinding.onCreateView() {
//        setAdapter()
//
//        observeReviewCheck()
//        nextMovePage()
//
//    }
//
//    override fun FragmentReviewBinding.onViewCreated() {
//    }
//
//    private fun setAdapter() {
//        recyclerView.apply {
//            this.adapter = reviewAdapter
//            this.layoutManager =
//                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            this.setHasFixedSize(false)
//        }
//    }
//
//    private fun observeReviewCheck() = lifecycleScope.launch {
//        reviewViewModel.getReviewCheck("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjI1LCJyb2xlIjowLCJpYXQiOjE2MzEwOTkyNjYsImV4cCI6MTYzMTEwMjg2NiwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.PIxL6ByfXP08cpC9cgOjO1azZM3IYiAR4IYxMlf6418")
//        reviewViewModel.reviewItem.observe(viewLifecycleOwner, { data ->
//
//            reviewAdapter.setItemList(data)
//
//
//        })
//
//    }
//
//    private fun nextMovePage() {
//        binding.nextImage.setOnClickListener {
//            findNavController().navigate(R.id.action_reviewFragment_to_reviewRegister)
//        }
//    }
//
//}
