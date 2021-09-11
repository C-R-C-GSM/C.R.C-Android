package com.example.crc_android.viewmodel.register

import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crc_android.data.model.dto.ResponseMessageDTO
import com.example.crc_android.data.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {

    //회원가입 순서
    val flag: LiveData<Int> get() = _flag
    private val _flag = MutableLiveData<Int>()

    //텍스트 공백인지 체크 등
    val errorMessage: LiveData<String> get() = _errorMessage
    private val _errorMessage = MutableLiveData<String>()

    //처음 회원가입 이메일 부분에서 뒤로가기 클릭
    val firstBackBtn: LiveData<Boolean> get() = _firstBackBtn
    private val _firstBackBtn = MutableLiveData<Boolean>()

    //signup email
    val email get() = _email
    private val _email: MutableLiveData<String> = MutableLiveData<String>()

    //signup password
    val password get() = _password
    private val _password: MutableLiveData<String> = MutableLiveData<String>()

    //signup name
    val name get() = _name
    private val _name: MutableLiveData<String> = MutableLiveData<String>()

    //signup 학년, 반, 번호
    val classNumber get() = _classNumber
    private val _classNumber: MutableLiveData<String> = MutableLiveData<String>()

    val registerResponse: LiveData<Response<ResponseMessageDTO?>> get() = _registerResponse
    private val _registerResponse = MutableLiveData<Response<ResponseMessageDTO?>>()


    init {
        _flag.value = 0
        _email.value = "null"
        _password.value = "null"
        _name.value = "null"
        _classNumber.value = "null"
    }


    fun setEmail(email: String) {
        if (TextUtils.isEmpty(email))
            _errorMessage.value = "plz input email"
        else {
            if (useRegex(email)) {
                this._email.value = email
                plusFlag()
            } else _errorMessage.value = "plz input gsm email"

        }

    }

    fun setPassword(password: String, checkPassword: String) {
        if (TextUtils.isEmpty(password))
            _errorMessage.value = "plz input password"
        else
            if (TextUtils.isEmpty(checkPassword)) {
                _errorMessage.value = "plz input checkPassword"
            } else {
                if (password == checkPassword) {
                    this._password.value = password
                    plusFlag()
                } else {
                    _errorMessage.value = "password, checkPassword not same"
                }
            }
    }

    fun setName(name: String) {
        if (TextUtils.isEmpty(name)) {
            _errorMessage.value = "plz input name"
        } else {
            this._name.value = name
            plusFlag()
        }
    }

    fun setClassNumber(classNumber: String) {
        if (TextUtils.isEmpty(classNumber) || classNumber.length < 4) {
            _errorMessage.value = "plz input classNumber"
        } else {
            this._classNumber.value = classNumber
            registerApiCall()
        }

    }

    fun plusFlag() {
        _flag.value = _flag.value?.plus(1)
    }

    fun minusFlag() {
        _flag.value = _flag.value?.minus(1)
    }

    fun setFirstBackBtn() {
        _firstBackBtn.value = true
    }

    fun registerApiCall() =
        viewModelScope.launch {
            repository.registerApi(_email.value, _password.value, _name.value, _classNumber.value)
                .let { response ->
                    _registerResponse.value = response
                }
        }

    //이메일 정규식 필터
    private fun useRegex(input: String): Boolean {
        val regex = Regex(
            pattern = "^[a-zA-Z][0-9][0-9][0-9][0-9][0-9]+@[a-zA-Z]sm\\.hs\\.kr\$",
            options = setOf(RegexOption.IGNORE_CASE)
        )
        return regex.matches(input)
    }
}