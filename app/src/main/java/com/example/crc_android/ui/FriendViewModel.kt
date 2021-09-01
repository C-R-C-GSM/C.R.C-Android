package com.example.crc_android.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.models.Data
import com.example.crc_android.data.models.FriendUserData
import com.example.crc_android.data.repository.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {


    private var _friendEnterItem = MutableLiveData<List<Data>>()
    val friendEnterItem: LiveData<List<Data>> get() = _friendEnterItem




    suspend fun getFriendOne(token: String, student_check: Int) = viewModelScope.launch {
        friendRepository.getFriendOne(token).let { response ->
            if (response.isSuccessful) {

                when (student_check) {
                    1 -> _friendEnterItem.value = response.body()?.data
                    0 -> _friendEnterItem.value = response.body()?.data
                }


            }

        }


    }


    suspend fun getFriendTwo(token: String, student_check: Int) = viewModelScope.launch {
        friendRepository.getFriendTwo(token).let { response ->
            if (response.isSuccessful) {

                when (student_check) {
                    1 -> _friendEnterItem.value = response.body()?.data
                    0 -> _friendEnterItem.value = response.body()?.data
                }


            }

        }

    }

    suspend fun getFriendThree(token: String,student_check:Int) = viewModelScope.launch {
        friendRepository.getFriendThree(token).let { response ->
            if (response.isSuccessful) {

                when (student_check) {
                    1 -> _friendEnterItem.value = response.body()?.data
                    0 -> _friendEnterItem.value = response.body()?.data
                }


            }

        }

    }

    companion object {
        const val TAG = "FriendViewModel"
    }
}