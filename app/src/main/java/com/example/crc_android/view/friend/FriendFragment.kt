package com.example.crc_android.view.friend

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.R
import com.example.crc_android.adapter.ChooseEnter
import com.example.crc_android.adapter.FriendAdapter
import com.example.crc_android.data.network.model.Data
import com.example.crc_android.databinding.FragmentFriendBinding
import com.example.crc_android.viewmodel.friend.FriendViewModel
import com.example.movie.base.UtilityBase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FriendFragment : UtilityBase.BaseFragment<FragmentFriendBinding>(R.layout.fragment_friend) {
    private val viewModel: FriendViewModel by viewModels()


    private val friendEnterAdapter: FriendAdapter by lazy {
        FriendAdapter(ChooseEnter.ENTER)
    }

    private val friendNoEnterAdapter: FriendAdapter by lazy {
        FriendAdapter(ChooseEnter.NO_ENTER)
    }



    override fun FragmentFriendBinding.onCreateView() {
        binding.fragment = this@FriendFragment

        setAdapter(friendEnterAdapter, binding.friendEnterRecyclerview)
        setAdapter(friendNoEnterAdapter, binding.friendNoEnterEcyclerview)
        buttonSelect(binding.oneBtn)
    }

    // findItem을 가져오고 student_check 가 0,1 인지 판단하여 adapter를 refresh 해준다.
    private fun observeFindItemGet(){
        viewModel.friendEnterItem.observe(viewLifecycleOwner    , { data ->
            observeStudentCheck(data, data[0].student_check)
        })
    }

    fun buttonSelect(button: View) {

        when (button) {

            binding.oneBtn -> {

                btnTextChangeColor(ONE, binding.oneBtn, binding.twoBtn, binding.threeBtn)

                lifecycleScope.launch {

                    observeFriend(1)
                    observeFindItemGet()

                }
            }
            binding.twoBtn -> {


                btnTextChangeColor(TWO, binding.oneBtn, binding.twoBtn, binding.threeBtn)


                lifecycleScope.launch {

                    observeFriend(2)
                    observeFindItemGet()

                }
            }
            binding.threeBtn -> {

                btnTextChangeColor(THREE, binding.oneBtn, binding.twoBtn, binding.threeBtn)



                lifecycleScope.launch {

                    observeFriend(3)
                    observeFindItemGet()


                }
            }
        }
    }

    private fun btnTextChangeColor(
        number: Int,
        oneText: Button,
        twoText: Button,
        threeText: Button
    ) {
        when (number) {
            ONE -> {
                oneText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                twoText.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_text_color))
                threeText.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_text_color))
            }
            TWO -> {
                oneText.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_text_color))
                twoText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                threeText.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_text_color))
            }
            THREE -> {
                oneText.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_text_color))
                twoText.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_text_color))
                threeText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }
        }
    }

    private fun observeStudentCheck(data: List<Data>?, number: Int?) {
        when (number) {
            0 -> {
                data?.filter { it.student_check == 0 }.apply {
                    friendNoEnterAdapter.setData(data)
                }
            }
            1 -> {
                data?.filter { it.student_check == 1 }.apply {
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
                    this.setHasFixedSize(false)
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

    private suspend fun observeFriend(number: Int) {

        when (number) {
            ONE -> viewModel.getFriendOne("", number)
            TWO -> viewModel.getFriendTwo("", number)
            THREE -> viewModel.getFriendThree("", number)
        }
    }

    companion object {
        const val ONE = 1
        const val TWO = 2
        const val THREE = 3
    }


}
