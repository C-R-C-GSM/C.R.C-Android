package com.example.crc_android.view.suggest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.R
import com.example.crc_android.adapter.MyAdapter
import com.example.crc_android.adapter.RegisterMyAdapter
import com.example.crc_android.adapter.SuggestAdapter
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.data.Data
import com.example.crc_android.databinding.FragmentSuggestionBinding
import com.example.crc_android.util.AES256
import com.example.crc_android.viewmodel.login.LoginViewModel
import com.example.crc_android.viewmodel.suggest.SuggestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuggestionFragment: UtilityBase.BaseFragment<FragmentSuggestionBinding>(R.layout.fragment_suggestion)
 {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val viewModel : SuggestViewModel by viewModels()
    private val loginViewModel : LoginViewModel by viewModels()

    override fun FragmentSuggestionBinding.onCreateView(

    ) {
        // Inflate the layout for this fragment
        viewManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, true)
        viewAdapter = SuggestAdapter(Data.suggestList,this@SuggestionFragment)

        binding.plus.setOnClickListener {
            findNavController().navigate(R.id.action_suggestionFragment_to_registractionFragment)
        }

        recyclerView = binding.recyclerviewSuggest.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }

        viewModel.dataList.observe(requireActivity(),{
            if (it.suggest_data !=null){
                Data.suggestList = it.suggest_data
                binding.recyclerviewSuggest.adapter = SuggestAdapter(Data.suggestList,this@SuggestionFragment)
            }
        })

        loginViewModel.readToken.asLiveData().observe(viewLifecycleOwner){
            viewModel.getsuggest(AES256.aesDecode(it.token).toString())
        }
    }
}




