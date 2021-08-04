package com.example.crc_android.ui.review

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.R
import com.example.crc_android.adapter.ReviewCheckAdapter
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.FragmentReviewBinding
import kotlinx.coroutines.launch


class ReviewFragment : UtilityBase.BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {

    private val reviewViewModel: ReviewViewModel by viewModels()
    private val reviewAdapter: ReviewCheckAdapter by lazy {
        ReviewCheckAdapter()
    }

    private val recyclerView: RecyclerView by lazy {
        binding.reviewCheckRecycler
    }

    override fun FragmentReviewBinding.onCreateView() {
        observeReviewCheck()
    }

    override fun FragmentReviewBinding.onViewCreated() {
        setAdapter()
    }

    private fun setAdapter() {
        recyclerView.apply {
            this.adapter = reviewAdapter
            this.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            this.setHasFixedSize(false)
        }
    }

    private fun observeReviewCheck() = lifecycleScope.launch {
        reviewViewModel.reviewItem.observe(viewLifecycleOwner, { data ->
            reviewViewModel.getReviewCheck("")

            // 값이 비어있지 않을 때
            if (data.isNotEmpty()) {
                // 값을 넣는다
                reviewAdapter.setItemList(data)
            }

        })


    }

}
