package com.example.crc_android.viewmodel.friend

import android.util.Log
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
    val TAG = "FriendViewModel"

    private val _friendEnterItem = MutableLiveData<List<Data>>()
    val friendEnterItem: LiveData<List<Data>> get() = _friendEnterItem

    private val _friendNoEnterItem = MutableLiveData<List<Data>>()
    val friendNoEnterItem: LiveData<List<Data>> get() = _friendNoEnterItem


    suspend fun getFriendOne(token: String) = viewModelScope.launch {

        try {
            friendRepository.getFriendOne(token).let { response ->
                if (response.isSuccessful) {
                    Log.d(TAG, "getFriendOne: ")
                    response.body()?.data?.filter { it.student_check == 1 }.apply {
                        _friendEnterItem.value = this
                    }

                    response.body()?.data?.filter { it.student_check == 0 }.apply {
                        _friendNoEnterItem.value = this
                    }


                }
            }

        } catch (e: Exception) {
            Throwable(e)

        }


    }


    suspend fun getFriendTwo(token: String) = viewModelScope.launch {
        try {

            friendRepository.getFriendTwo(token).let { response ->
                if (response.isSuccessful) {
                    Log.d(TAG, "FriendViewModel - getFriendTwo() called")
                    response.body()?.data?.filter { it.student_check == 1 }.apply {
                        _friendEnterItem.value = this
                    }

                    response.body()?.data?.filter { it.student_check == 0 }.apply {
                        _friendNoEnterItem.value = this
                    }


                }

            }
        } catch (e: Exception) {
            Throwable(e)
        }

    }

    suspend fun getFriendThree(token: String) = viewModelScope.launch {
        try {
            friendRepository.getFriendThree(token).let { response ->
                if (response.isSuccessful) {
Log.d(TAG,"FriendViewModel - getFriendThree() called")
                    response.body()?.data?.filter { it.student_check == 1 }.apply {
                        _friendEnterItem.value = this
                    }

                    response.body()?.data?.filter { it.student_check == 0 }.apply {
                        _friendNoEnterItem.value = this
                    }
                }
            }
        } catch (e: Exception) {
            Throwable(e)
        }


    }


}