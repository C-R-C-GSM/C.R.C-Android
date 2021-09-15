package com.example.crc_android.viewmodel.admin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.repository.AdminRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    val repository: AdminRepository
) : ViewModel() {

    private val _adminSuccess = MutableLiveData<Boolean>()
    val adminSuccess: LiveData<Boolean> get() = _adminSuccess

    init {
        _adminSuccess.value = false
    }

    suspend fun getCheckRole(token: String) = viewModelScope.launch {
        repository.getCheckRole(token).let {
            if (it.isSuccessful) {
                Log.d("TAG", "getCheckRole: ${it}")
            } else {
                _adminSuccess.value = false
                Log.d("TAG", "getCheckRole: 실패")
            }
        }
    }

}

