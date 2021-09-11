package com.example.crc_android.viewmodel.friend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.network.model.Data
import com.example.crc_android.data.repository.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

// TODO: dto는 똑같으니 one,two,three 함수를 하나로 모을까?
@HiltViewModel
class FriendViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {


    private val _friendEnterItem = MutableLiveData<List<Data>>()
    val friendEnterItem: LiveData<List<Data>> get() = _friendEnterItem


    suspend fun getFriendOne(token: String, student_check: Int) = viewModelScope.launch {

        try {
            friendRepository.getFriendOne(token).let { response ->
                if (response.isSuccessful) {

                    when (student_check) {
                        1 -> _friendEnterItem.value = response.body()?.data
                        0 -> _friendEnterItem.value = response.body()?.data
                    }


                }
            }

        } catch (e: Exception) {
            Throwable(e)

        }


    }


    suspend fun getFriendTwo(token: String, student_check: Int) = viewModelScope.launch {
        try {
            friendRepository.getFriendTwo(token).let { response ->
                if (response.isSuccessful) {

                    when (student_check) {
                        1 -> _friendEnterItem.value = response.body()?.data
                        0 -> _friendEnterItem.value = response.body()?.data
                    }


                }

            }
        } catch (e: Exception) {
            Throwable(e)
        }

    }

    suspend fun getFriendThree(token: String, student_check: Int) = viewModelScope.launch {
        try {
            friendRepository.getFriendThree(token).let { response ->
                if (response.isSuccessful) {

                    when (student_check) {
                        1 -> _friendEnterItem.value = response.body()?.data
                        0 -> _friendEnterItem.value = response.body()?.data
                    }
                }
            }
        } catch (e: Exception) {
            Throwable(e)
        }


    }


}