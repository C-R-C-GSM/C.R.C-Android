package com.example.crc_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.R
import com.example.crc_android.data.network.model.check.ReviewData
import com.example.crc_android.databinding.ReviewItemRowBinding

class ReviewCheckAdapter : RecyclerView.Adapter<ReviewCheckAdapter.ReviewCheckHolder>() {
    private val reviewList = mutableListOf<ReviewData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: String)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ReviewCheckHolder(val binding: ReviewItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReviewData) {
            binding.item = item
            binding.executePendingBindings()

            binding.reviewContact.setOnClickListener {
                listener?.onItemClick(it, item.reviewid.toString())
            }
        }



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewCheckHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ReviewItemRowBinding = DataBindingUtil
            .inflate(
                layoutInflater, R.layout.review_item_row,
                parent, false
            )
        return ReviewCheckHolder(binding)

    }


    override fun onBindViewHolder(holder: ReviewCheckHolder, position: Int) {
        val item = reviewList[position]

        holder.bind(item)


    }

    override fun getItemCount() = reviewList.size
    fun setItemList(data: List<ReviewData>) {
        // 데이터가 바뀌였으면
        val movieDiffUtil = ReviewDiffUtil(reviewList, data)
        // calculateDiff 아이템 변경을 감지한다.
        val diffUtilResult = movieDiffUtil.let { DiffUtil.calculateDiff(it) }
        // 데이터를 없애고
        reviewList.clear()
        // 새로운 데이터로 갈아끼운다.
        reviewList.addAll(data)

        //어뎁터에 감지된 데이터를 전달한다.
        diffUtilResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }
}

class ReviewDiffUtil(
    private val oldList: List<ReviewData>,
    private val newList: List<ReviewData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]


    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {


        return getChangePayload(
            oldItemPosition,
            newItemPosition
        )
    }


}