package com.example.crc_android.view.friend

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.R
import com.example.crc_android.adapter.ChooseEnter
import com.example.crc_android.adapter.FriendAdapter
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.data.network.model.Data
import com.example.crc_android.databinding.FragmentFriendBinding
import com.example.crc_android.viewmodel.friend.FriendViewModel
import com.example.crc_android.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FriendFragment : UtilityBase.BaseFragment<FragmentFriendBinding>(R.layout.fragment_friend) {
    private val viewModel: FriendViewModel by viewModels()
    private val loginViewModel by viewModels<LoginViewModel>()

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


    // 버튼클릭할때마다 event
    fun buttonSelect(button: View) {

        when (button) {

            binding.oneBtn -> {
                Log.d("TAG", "buttonSelect: 1")
                btnTextChangeColor(ONE, binding.oneBtn, binding.twoBtn, binding.threeBtn)
                observeFriend(ONE)
                observeFindItemGet(ONE)
            }
            binding.twoBtn -> {
                Log.d("TAG", "buttonSelect: 2")
                btnTextChangeColor(TWO, binding.oneBtn, binding.twoBtn, binding.threeBtn)

                observeFriend(TWO)
                observeFindItemGet(TWO)


            }
            binding.threeBtn -> {
                Log.d("TAG", "buttonSelect: 3")
                btnTextChangeColor(THREE, binding.oneBtn, binding.twoBtn, binding.threeBtn)

                observeFriend(THREE)
                observeFindItemGet(THREE)
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
                twoText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_text_color
                    )
                )
                threeText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_text_color
                    )
                )
            }
            TWO -> {
                oneText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_text_color
                    )
                )
                twoText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                threeText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_text_color
                    )
                )
            }
            THREE -> {
                oneText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_text_color
                    )
                )
                twoText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_text_color
                    )
                )
                threeText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }
        }
    }


    // 데이터를 받는다.
    private fun observeFriend(number: Int) {
        loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                when (number) {
                    ONE -> {
                        viewModel.getFriendOne(it.token)
                    }
                    TWO -> {
                        viewModel.getFriendTwo(it.token)
                    }
                    THREE -> {
                        viewModel.getFriendThree(it.token)
                    }
                }
            }
        }
    }

    // 데이터를 refresh 해준다.
    private fun observeFindItemGet(number: Int) {
        when (number) {
            ONE -> {
                viewModel.friendEnterItem.observe(viewLifecycleOwner, { data ->

                    adapterRefresh(data, friendEnterAdapter)

                })
                viewModel.friendNoEnterItem.observe(viewLifecycleOwner) { data ->
                    adapterRefresh(data, friendNoEnterAdapter)
                }

            }
            TWO -> {
                viewModel.friendEnterItem.observe(viewLifecycleOwner, { data ->

                    adapterRefresh(data, friendEnterAdapter)

                })
                viewModel.friendNoEnterItem.observe(viewLifecycleOwner) { data ->
                    adapterRefresh(data, friendNoEnterAdapter)
                }
            }
            THREE -> {
                viewModel.friendEnterItem.observe(viewLifecycleOwner, { data ->

                    adapterRefresh(data, friendEnterAdapter)

                })
                viewModel.friendNoEnterItem.observe(viewLifecycleOwner) { data ->
                    adapterRefresh(data, friendNoEnterAdapter)
                }
            }
        }
    }


    private fun adapterRefresh(data: List<Data>, adapter: FriendAdapter) {
        when (adapter) {
            friendEnterAdapter -> friendEnterAdapter.setData(data)

            friendNoEnterAdapter -> friendNoEnterAdapter.setData(data)
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


    companion object {
        const val ONE = 1
        const val TWO = 2
        const val THREE = 3
    }


}
