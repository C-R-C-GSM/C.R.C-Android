package com.example.crc_android.view.suggest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.data.RetrofitHelper
import com.example.crc_android.data.network.model.suggest.CommentData
import com.example.crc_android.data.network.model.suggest.SuggestReplyRequest
import com.example.crc_android.databinding.FragmentRegistractionBinding
import com.example.crc_android.databinding.FragmentSuggestioncommentBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.util.App
import com.example.crc_android.viewmodel.login.LoginViewModel
import com.example.crc_android.viewmodel.suggest.SuggestViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SuggestioncommentFragment : UtilityBase.BaseFragment<FragmentSuggestioncommentBinding>(R.layout.fragment_suggestioncomment)
{

    private val TAG = "RegistractionActivity"
    val suggestViewModel: SuggestViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()


    override fun FragmentSuggestioncommentBinding.onCreateView(

    ){
        binding.leftarrow.setOnClickListener {
            findNavController().navigate(R.id.action_suggestcommentFragment_to_suggestcontentFragment)

        }

        binding.add1.setOnClickListener {
            addData()

        }
        suggestViewModel.comment.observe(requireActivity(),{

            if (it.success){
                App.reply = binding.content.text.toString()
                findNavController().navigate(R.id.action_suggestcommentFragment_to_suggestcontentFragment)

            }
        })

    }

    private fun addData() {
        val comment = binding.content.text.toString()
        Log.d(TAG, "addData: 테스트 $comment ")
        if (comment.isBlank()){
            Toast.makeText(requireContext(), "댓글을 적어주세요", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "addData: $comment")
        } else {
            var blog = SuggestReplyRequest(comment, App.id)
            loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner){
                suggestViewModel.postreply(AES256.aesDecode(it.token).toString(),blog)
            }
            binding.content.text
        }
    }
}





