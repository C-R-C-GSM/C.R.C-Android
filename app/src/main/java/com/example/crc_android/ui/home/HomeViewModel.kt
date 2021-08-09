package com.example.crc_android.ui.home

import android.util.Log
import androidx.compose.ui.input.key.Key.Companion.Minus
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


    private val _enterNumber: MutableLiveData<Int> = MutableLiveData<Int>()
    val enterNumber: LiveData<Int> get() = _enterNumber

    private val _noEntryNumber: MutableLiveData<Int> = MutableLiveData<Int>()
    val noEntryNumber: LiveData<Int> get() = _noEntryNumber


    fun getTotalNumber(token: String) = viewModelScope.launch {
        val data = friendRepository.totalFriend(token)
        if (data.isSuccessful) {
            Log.d(TAG, "getTotalNumber: 성공")
            Log.d(TAG, "getTotalNumber: ${data.body()?.total_num}")

            // 입장
            _enterNumber.value = data.body()?.total_num
            //미입장
            _noEntryNumber.value=230- data.body()!!.total_num

        }
    }

    init {
        _enterNumber.value = 0
        _noEntryNumber.value = 0
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}

