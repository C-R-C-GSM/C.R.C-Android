package com.example.crc_android.ui.home

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
    private val _totalNumber: MutableLiveData<Int> = MutableLiveData<Int>()
    val totalNumber: LiveData<Int> get() = _totalNumber

    fun getTotalNumber(token: String) = viewModelScope.launch {
        val data = friendRepository.totalFriend(token)
        if (data.isSuccessful) {
            Log.d(TAG, "getTotalNumber: 성공")
            Log.d(TAG, "getTotalNumber: ${data.body()?.total_num}")
            _totalNumber.value = data.body()?.total_num
        }
    }

    init {
        _totalNumber.value = 0
    }
    companion object{
        const val TAG="HomeViewModel"
    }
}