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

    private var _friendNoEntryEnterItem = MutableLiveData<List<Data>>()
    val friendNoEntryEnterItem: LiveData<List<Data>> get() = _friendNoEntryEnterItem

    suspend fun getFriendOne(token: String) = viewModelScope.launch {
        val data = friendRepository.getFriendOne(token)
        if (data.isSuccessful) {

            if (data.body() != null) {

                data.body()?.data?.filter { it.student_check == 1 }?.apply {
                    _friendEnterItem.postValue(this)
                    Log.d(TAG, "getFriendOne check == 1: ${this[0].student_name}")
                }
                data.body()?.data?.filter { it.student_check == 0 }?.apply {
                    _friendNoEntryEnterItem.postValue(this)
                    Log.d(TAG, "getFriendOne check == 0: ${this[0].student_name}")
                }


            }
        }

    }


    suspend fun getFriendTwo(token: String) = viewModelScope.launch {
        val data = friendRepository.getFriendTwo(token)
        if (data.isSuccessful) {

            if (data.body() != null) {

                data.body()?.data?.filter { it.student_check == 1 }?.apply {
                    _friendEnterItem.postValue(this)
                    Log.d(TAG, "getFriendOne check == 1: ${this[0].student_name}")
                }
                data.body()?.data?.filter { it.student_check == 0 }?.apply {
                    _friendNoEntryEnterItem.postValue(this)
                    Log.d(TAG, "getFriendOne check == 0: ${this[0].student_name}")
                }


            }
        }

    }
    suspend fun getFriendThree(token: String) = viewModelScope.launch {
        val data = friendRepository.getFriendThree(token)
        if (data.isSuccessful) {

            if (data.body() != null) {

                data.body()?.data?.filter { it.student_check == 1 }?.apply {
                    _friendEnterItem.postValue(this)
                    Log.d(TAG, "getFriendOne check == 1: ${this[0].student_name}")
                }
                data.body()?.data?.filter { it.student_check == 0 }?.apply {
                    _friendNoEntryEnterItem.postValue(this)
                    Log.d(TAG, "getFriendOne check == 0: ${this[0].student_name}")
                }


            }
        }

    }

    companion object {
        const val TAG = "FriendViewModel"
    }
}