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


    suspend fun getCheckRole(token: String) = viewModelScope.launch {
        repository.getCheckRole(token).let {
            Log.d("TAG", "getCheckRole: ${it.body()}")
            when (it.body()?.code) {
                600 -> _adminSuccess.value = false
                0 -> _adminSuccess.value = true
                404 -> _adminSuccess.value = false
                else -> _adminSuccess.value = false
            }
        }
    }

}

