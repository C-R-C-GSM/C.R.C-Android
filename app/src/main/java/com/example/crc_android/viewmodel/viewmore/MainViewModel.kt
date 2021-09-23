package com.example.crc_android.viewmodel.viewmore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.Data
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.data.repository.NoticeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository : NoticeRepository
) : ViewModel(){

    private val _response = MutableLiveData<NoticeToken>()
    val response : LiveData<NoticeToken>
        get() = _response

    private val _dataList = MutableLiveData<NoticeToken>()
    val dataList : LiveData<NoticeToken>
        get() = _dataList

    fun postNotice(token : String, request : RegistNotice){
        viewModelScope.launch {
            repository.postNoticeToken(token, request).let {
                _response.value = it.body()
            }
        }
    }

    fun getNotice(token : String){
        viewModelScope.launch {
            repository.getNoticeToken(token).let {
                _dataList.value = it.body()
            }
        }
    }
}