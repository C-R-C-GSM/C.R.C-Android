package com.example.crc_android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.models.check.ReviewCheck
import com.example.crc_android.data.models.check.ReviewData
import com.example.crc_android.data.repository.ReviewRepository
import com.example.crc_android.data.repository.ReviewRepository_Factory
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

        reviewRepository.getReviewCheck(token).let { response ->
            if (response.isSuccessful) {
                _reviewItem.postValue(response.body()?.review_data)

            }
        }

    }
}