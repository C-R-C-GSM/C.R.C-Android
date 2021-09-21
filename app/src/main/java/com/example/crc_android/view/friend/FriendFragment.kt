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
import com.example.crc_android.util.AES256
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



    // 버튼클릭할때마다 각각 통신
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


    // 버튼클릭할때마다 버튼색깔을 바꿔주는 함수
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


    // 서버와 통신한다.
    private fun observeFriend(number: Int) {
        loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner) {
            Log.d("TAG", "FriendFragment - observeFriend() called ${it.token}")
            lifecycleScope.launch {
                when (number) {
                    ONE -> {
                        viewModel.getFriendOne(AES256.aesDecode(it.token).toString())
                    }
                    TWO -> {
                        viewModel.getFriendTwo(AES256.aesDecode(it.token).toString())
                    }
                    THREE -> {
                        viewModel.getFriendThree(AES256.aesDecode(it.token).toString())
                    }
                }
            }
        }
    }

    // 데이터가 들어오면 check 구별후 refresh 해준다.
    private fun observeFindItemGet(number: Int) {
        when (number) {
            ONE -> {
                viewModel.friendEnterItem.observe(viewLifecycleOwner, { data ->
                    studentNumber(data, 1)
                    adapterRefresh(data, friendEnterAdapter)

                })
                viewModel.friendNoEnterItem.observe(viewLifecycleOwner) { data ->
                    studentNumber(data, 0)
                    adapterRefresh(data, friendNoEnterAdapter)
                }

            }
            TWO -> {
                viewModel.friendEnterItem.observe(viewLifecycleOwner, { data ->
                    studentNumber(data, 1)
                    adapterRefresh(data, friendEnterAdapter)

                })
                viewModel.friendNoEnterItem.observe(viewLifecycleOwner) { data ->
                    studentNumber(data, 0)
                    adapterRefresh(data, friendNoEnterAdapter)
                }
            }
            THREE -> {
                viewModel.friendEnterItem.observe(viewLifecycleOwner, { data ->
                    studentNumber(data, 1)
                    adapterRefresh(data, friendEnterAdapter)

                })
                viewModel.friendNoEnterItem.observe(viewLifecycleOwner) { data ->
                    studentNumber(data, 0)
                    adapterRefresh(data, friendNoEnterAdapter)
                }
            }
        }
    }


    // 입장,미입장 어뎁터가 변동사항이있으면 refresh 한다.
    private fun adapterRefresh(data: List<Data>, adapter: FriendAdapter) {
        when (adapter) {
            friendEnterAdapter -> {
                refreshAdapter(data,adapter)
                friendEnterAdapter.setData(data)
            }

            friendNoEnterAdapter -> {
                friendNoEnterAdapter.setData(data)
            }
        }

    }

    // student가 check가 됐는가 안됐는거에 따라서 입장과 미입장을 구별한다.
    private fun studentNumber(data: List<Data>, number: Int) = when (number) {
        1 -> {
            val totalData = data.filter { it.student_check == 1 }
            binding.totalNumberText.text = totalData.size.toString()
        }
        0 -> {
            val dataValue = data.filter { it.student_check == 0 }
            binding.totalNoEnterText.text = dataValue.size.toString()
        }


        else -> {
            binding.totalNumberText.text = 0.toString()
            binding.totalNoEnterText.text = 0.toString()
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

    private fun refreshAdapter(data: List<Data>, adapter: FriendAdapter) {
        binding.refreshImg.setOnClickListener{
            adapterRefresh(data,adapter)
        }
    }


    companion object {
        const val ONE = 1
        const val TWO = 2
        const val THREE = 3
    }


}
