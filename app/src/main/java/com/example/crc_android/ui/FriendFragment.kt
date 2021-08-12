package com.example.crc_android.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.R
import com.example.crc_android.adapter.ChooseEnter
import com.example.crc_android.adapter.FriendAdapter
import com.example.crc_android.databinding.FragmentFriendBinding
import com.example.crc_android.databinding.FriendListNoEntryEnterBinding
import com.example.movie.base.UtilityBase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FriendFragment : UtilityBase.BaseActivity<FragmentFriendBinding>(R.layout.fragment_friend) {
    private val viewModel: FriendViewModel by viewModels()

    private lateinit var friendAdapter: FriendAdapter
    private lateinit var friendAdapter1: FriendAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.fragment = this
        buttonSelect(binding.oneBtn)
    }

    @SuppressLint("ResourceAsColor")
    fun buttonSelect(button: View) {
        when (button) {
            binding.oneBtn -> {

                lifecycleScope.launch {


                    viewModel.getFriendOne("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjMyLCJyb2xlIjowLCJpYXQiOjE2Mjg3NzY0NTEsImV4cCI6MTYyODc4MDA1MSwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.smtbHP8hA4TYXiCGo8fX6iKrj0ECu28XNmr6uFHujfM")



                    viewModel.friendEnterItem.observe(this@FriendFragment, { data ->
                        if (data != null) {
                            Log.d(TAG, "Enter: ${data[0].student_name} :${data[0].student_data}")
                            friendAdapter = FriendAdapter(ChooseEnter.ENTER)
                            setAdapter(friendAdapter,binding.friendEnterRecyclerview)
                            friendAdapter.setData(data)
                        }
                    })
                    viewModel.friendNoEntryEnterItem.observe(this@FriendFragment, { data ->
                        if (data != null) {
                            Log.d(TAG, "noEntryEnter: ${data[0].student_name} : ${data[0].student_data} ")
                            friendAdapter1 = FriendAdapter(ChooseEnter.NO_ENTER)
                            setAdapter(friendAdapter1,binding.friendNoEnterEcyclerview)
                            friendAdapter1.setData(data)
                        }

                    })

                }
            }
            binding.twoBtn -> {


                binding.oneBtn.setTextColor(R.color.button_text_color)
                binding.twoBtn.setTextColor(R.color.button_text_color)
                binding.oneBtn.setTextColor(R.color.button_select_text_color)



                lifecycleScope.launch {
                    viewModel.getFriendTwo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjMyLCJyb2xlIjowLCJpYXQiOjE2Mjg3NzY0NTEsImV4cCI6MTYyODc4MDA1MSwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.smtbHP8hA4TYXiCGo8fX6iKrj0ECu28XNmr6uFHujfM")

                    viewModel.friendEnterItem.observe(this@FriendFragment, { data ->
                        if (data != null) {
                            Log.d(TAG, "Enter: ${data[0].student_name} :${data[0].student_data}")
                            friendAdapter = FriendAdapter(ChooseEnter.ENTER)
                            setAdapter(friendAdapter,binding.friendEnterRecyclerview)
                            friendAdapter.setData(data)
                        }
                    })
                    viewModel.friendNoEntryEnterItem.observe(this@FriendFragment, { data ->
                        if (data != null) {
                            Log.d(TAG, "noEntryEnter: ${data[0].student_name} : ${data[0].student_data} ")
                            friendAdapter1 = FriendAdapter(ChooseEnter.NO_ENTER)
                            setAdapter(friendAdapter1,binding.friendNoEnterEcyclerview)
                            friendAdapter1.setData(data)
                        }

                    })

                }
            }
            binding.threeBtn -> {

                binding.oneBtn.setTextColor(R.color.button_text_color)
                binding.twoBtn.setTextColor(R.color.button_text_color)
                binding.oneBtn.setTextColor(R.color.button_select_text_color)



                lifecycleScope.launch {

                    viewModel.getFriendThree("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOjMyLCJyb2xlIjowLCJpYXQiOjE2Mjg3NzY0NTEsImV4cCI6MTYyODc4MDA1MSwiaXNzIjoiQy5SLkNfU0VSVkVSIn0.smtbHP8hA4TYXiCGo8fX6iKrj0ECu28XNmr6uFHujfM")
                    viewModel.friendEnterItem.observe(this@FriendFragment, { data ->
                        if (data != null) {
                            Log.d(TAG, "Enter: ${data[0].student_name} :${data[0].student_data}")
                            friendAdapter = FriendAdapter(ChooseEnter.ENTER)
                            setAdapter(friendAdapter,binding.friendEnterRecyclerview)
                            friendAdapter.setData(data)
                        }
                    })

                    viewModel.friendNoEntryEnterItem.observe(this@FriendFragment, { data ->
                        if (data != null) {
                            Log.d(TAG, "noEntryEnter: ${data[0].student_name} : ${data[0].student_data} ")
                            friendAdapter1 = FriendAdapter(ChooseEnter.NO_ENTER)
                            setAdapter(friendAdapter1,binding.friendNoEnterEcyclerview)
                            friendAdapter1.setData(data)
                        }

                    })
                }
            }
        }
    }


    private fun setAdapter(adapter: RecyclerView.Adapter<*>, recyclerView: RecyclerView) {
        when (recyclerView) {
            binding.friendEnterRecyclerview -> {

                binding.friendEnterRecyclerview.adapter = adapter
                binding.friendEnterRecyclerview.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.friendEnterRecyclerview.setHasFixedSize(false)
            }
            binding.friendNoEnterEcyclerview -> {
                binding.friendNoEnterEcyclerview.adapter = adapter
                binding.friendNoEnterEcyclerview.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.friendNoEnterEcyclerview.setHasFixedSize(false)
            }
        }
    }

    companion object {
        const val TAG = "FriendFragment"
    }
}
