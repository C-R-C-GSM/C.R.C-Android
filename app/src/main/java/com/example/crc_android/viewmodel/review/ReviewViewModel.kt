package com.example.crc_android.viewmodel.review

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.network.model.check.ReviewData
import com.example.crc_android.data.network.model.ReviewPostRequest
import com.example.crc_android.data.network.model.ReviewReplyRequest
import com.example.crc_android.data.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    private var _reviewItem = MutableLiveData<List<ReviewData>>()
    val reviewItem: LiveData<List<ReviewData>> get() = _reviewItem

    fun getReviewCheck(token: String) = viewModelScope.launch {
        try {
            reviewRepository.getReviewCheck(token).let { response ->
                if (response.isSuccessful) {
                    Log.d("TAG", "getReviewCheck: 성공")
                    _reviewItem.value = response.body()?.review_data

                }
            }

        }catch (e:Exception){
            Log.d("TAG", "getReviewCheck: $e")
        }
    }
    fun reviewRegister(token:String,reviewPostRequest: ReviewPostRequest)=viewModelScope.launch {
        reviewRepository.postReviewRegister(token,reviewPostRequest).let{
            if (it.isSuccessful){
                Log.d("TAG", "reviewRegister: 성공")
            }
        }
    }

    fun reviewReply(token:String,reviewReplyRequest: ReviewReplyRequest)=viewModelScope.launch {
        reviewRepository.postReviewReply(token,reviewReplyRequest).let{
            Log.d("TAG", "reviewReply: 성공")


        }
    }
}