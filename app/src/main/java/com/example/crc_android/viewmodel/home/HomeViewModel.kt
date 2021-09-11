package com.example.crc_android.viewmodel.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.repository.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {


    private val _enterNumber: MutableLiveData<String> = MutableLiveData<String>()
    val enterNumber: LiveData<String> get() = _enterNumber

    private val _noEntryNumber: MutableLiveData<String> = MutableLiveData<String>()
    val noEntryNumber: LiveData<String> get() = _noEntryNumber


    fun getTotalNumber(token: String) = viewModelScope.launch {
        friendRepository.totalFriend(token).let { data ->
            if (data.isSuccessful) {
                Log.d(TAG, "getTotalNumber: 성공")
                Log.d(TAG, "getTotalNumber: ${data.body()?.total_num}")

                // 입장
                _enterNumber.value = data.body()?.total_num.toString()
                //미입장
                _noEntryNumber.value = 230.minus(data.body()!!.total_num).toString()
                Log.d(TAG, "enter : ${_enterNumber.value} noEnter : ${_noEntryNumber.value}")

            }
        }
    }


    companion object {
        const val TAG = "HomeViewModel"
    }
}

