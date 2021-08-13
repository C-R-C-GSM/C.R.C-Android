package com.example.crc_android.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.R
import com.example.crc_android.data.models.Data
import com.example.crc_android.data.models.FriendUserData
import com.example.crc_android.databinding.FriendListEnterBinding
import com.example.crc_android.databinding.FriendListNoEntryEnterBinding

enum class ChooseEnter {
    ENTER, NO_ENTER
}

class FriendAdapter(private val chooseEnter: ChooseEnter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var friendList = mutableListOf<Data>()

    class FriendViewHolder(private val binding: FriendListEnterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(friendUserData: Data) {

            binding.data = friendUserData
            binding.executePendingBindings()


        }

        companion object {
            fun from(parent: ViewGroup): FriendViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: FriendListEnterBinding = DataBindingUtil
                    .inflate(
                        layoutInflater, R.layout.friend_list_enter,
                        parent, false
                    )
                return FriendViewHolder(binding)
            }
        }

    }

    class FriendNoEnterViewHolder(private val binding: FriendListNoEntryEnterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(friendUserData: Data) {

            binding.data = friendUserData
            binding.executePendingBindings()


        }

        companion object {

            fun from(parent: ViewGroup): FriendNoEnterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: FriendListNoEntryEnterBinding = DataBindingUtil
                    .inflate(
                        layoutInflater, R.layout.friend_list_no_entry_enter,
                        parent, false
                    )
                return FriendNoEnterViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (chooseEnter) {
             ChooseEnter.ENTER -> FriendViewHolder.from(parent)
            ChooseEnter.NO_ENTER ->{ FriendNoEnterViewHolder.from(parent)
            }


            else -> FriendViewHolder.from(parent)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is FriendViewHolder -> {
                holder.bindItem(friendList[position])

            }
            is FriendNoEnterViewHolder -> {
                holder.bindItem(friendList[position])
            }
        }
    }

    fun setData(friendData: List<Data>) {

        val movieDiffUtil = MovieDiffUtil(friendList, friendData)
        val diffUtilResult = movieDiffUtil.let { DiffUtil.calculateDiff(it) }
        friendList = friendData as MutableList<Data>
        diffUtilResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }


    override fun getItemCount() = friendList.size
}




class MovieDiffUtil(
    private val oldList: List<Data>,
    private val newList: List<Data>
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