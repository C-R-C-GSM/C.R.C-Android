package com.example.crc_android.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.R
import com.example.crc_android.adapter.ChooseEnter
import com.example.crc_android.adapter.FriendAdapter
import com.example.crc_android.data.models.Data
import com.example.crc_android.databinding.FragmentFriendBinding
import com.example.movie.base.UtilityBase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FriendFragment : UtilityBase.BaseActivity<FragmentFriendBinding>(R.layout.fragment_friend) {
    private val viewModel: FriendViewModel by viewModels()

     val TAG="FriendFragment"

    private val friendEnterAdapter: FriendAdapter by lazy {
        FriendAdapter(ChooseEnter.ENTER)
    }

    private val friendNoEnterAdapter: FriendAdapter by lazy {
        FriendAdapter(ChooseEnter.NO_ENTER)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.fragment = this

        setAdapter(friendEnterAdapter, binding.friendEnterRecyclerview)
        setAdapter(friendNoEnterAdapter, binding.friendNoEnterEcyclerview)
        buttonSelect(binding.oneBtn)
    }

    fun buttonSelect(button: View) {
        val a = ContextCompat.getColor(this, R.color.black)
        val b = ContextCompat.getColor(this, R.color.button_text_color)
        when (button) {

            binding.oneBtn -> {

                binding.oneBtn.setTextColor(a)
                binding.twoBtn.setTextColor(b)
                binding.threeBtn.setTextColor(b)

                lifecycleScope.launch {

                    observeFriend("", 1)

                    viewModel.friendEnterItem.observe(this@FriendFragment, { data ->
                        observeStudentCheck(data, data[0].student_check)
                    })


                }
            }
            binding.twoBtn -> {


                binding.oneBtn.setTextColor(b)
                binding.twoBtn.setTextColor(a)
                binding.threeBtn.setTextColor(b)



                lifecycleScope.launch {

                    observeFriend("", 2)
                    viewModel.friendEnterItem.observe(this@FriendFragment, { data ->
                            observeStudentCheck(data, data[0].student_check)
                    })


                }
            }
            binding.threeBtn -> {

                binding.oneBtn.setTextColor(b)
                binding.twoBtn.setTextColor(b)
                binding.threeBtn.setTextColor(a)



                lifecycleScope.launch {

                    observeFriend("", 3)
                    viewModel.friendEnterItem.observe(this@FriendFragment, { data ->

                        Log.d(TAG, "buttonSelect: ${data[0].student_check}")
                        observeStudentCheck(data, data[0].student_check)

                    })


                }
            }
        }
    }

    private fun observeStudentCheck(data: List<Data>, number: Int) {
        when (number) {
            0 -> {
                data.filter { it.student_check == 0 }.apply {
                    friendNoEnterAdapter.setData(data)
                }
            }
            1 -> {
                data.filter { it.student_check == 1 }.apply {
                    friendEnterAdapter.setData(data)
                }
            }

        }

    }


    private fun setAdapter(adapter: FriendAdapter, recyclerView: RecyclerView) {
        when (recyclerView) {
            binding.friendEnterRecyclerview -> {

                binding.friendEnterRecyclerview.apply {
                    this.adapter = adapter
                    this.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.friendEnterRecyclerview.setHasFixedSize(false)
                }
            }
            binding.friendNoEnterEcyclerview -> {
                binding.friendNoEnterEcyclerview.apply {
                    this.adapter = adapter
                    this.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    this.setHasFixedSize(false)
                }
            }
        }
    }

    private suspend fun observeFriend(token: String, number: Int) {

        when (number) {
            1 -> viewModel.getFriendOne(token, number)
            2 -> viewModel.getFriendTwo(token, number)
            3 -> viewModel.getFriendThree(token, number)
        }
    }


}
