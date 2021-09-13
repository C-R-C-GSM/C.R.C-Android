package com.example.crc_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.R
import com.example.crc_android.data.network.model.check.ReviewCheck
import com.example.crc_android.data.network.model.check.ReviewData
import com.example.crc_android.databinding.ReviewItemRowBinding

class ReviewCheckAdapter : RecyclerView.Adapter<ReviewCheckAdapter.ReviewCheckHolder>() {
    private val reviewList = mutableListOf<ReviewData>()

    class ReviewCheckHolder(val binding: ReviewItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReviewData) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ReviewCheckHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ReviewItemRowBinding = DataBindingUtil
                    .inflate(
                        layoutInflater, R.layout.review_item_row,
                        parent, false
                    )
                return ReviewCheckHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewCheckHolder =
        ReviewCheckHolder.from(parent)


    override fun onBindViewHolder(holder: ReviewCheckHolder, position: Int) {
        holder.bind(reviewList[position])
    }

    override fun getItemCount() = reviewList.size
}