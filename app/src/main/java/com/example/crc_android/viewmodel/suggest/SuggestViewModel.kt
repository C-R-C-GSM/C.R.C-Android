package com.example.crc_android.viewmodel.suggest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RegistNotice
import com.example.crc_android.data.network.model.suggest.RegistToken
import com.example.crc_android.data.network.model.suggest.RegisterRequest
import com.example.crc_android.data.network.model.suggest.SuggestReplyRequest
import com.example.crc_android.data.repository.SuggestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestViewModel @Inject constructor(
    val repository : SuggestRepository
) : ViewModel(){

    private val _response = MutableLiveData<RegistToken>()
    val response : LiveData<RegistToken>
        get() = _response

    private val _dataList = MutableLiveData<RegistToken>()
    val dataList : LiveData<RegistToken>
        get() = _dataList

    private val _comment = MutableLiveData<RegistToken>()
    val comment:LiveData<RegistToken>
        get()= _comment

    fun postregister(token : String, request : RegisterRequest){
        viewModelScope.launch {
            repository.postRegister(token, request).let {
                _response.value = it.body()
            }
        }
    }

    fun getsuggest(token : String){
        viewModelScope.launch {
            repository.getSuggetToken(token).let {
                _dataList.value = it.body()
            }
        }
    }

    fun postreply(token: String, request: SuggestReplyRequest){
        viewModelScope.launch {
            repository.postReply(token,request).let {
                _comment.value = it.body()
            }
        }
    }
}