package com.example.crc_android.view.viewmore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.adapter.MyAdapter
import com.example.crc_android.data.Data
import com.example.crc_android.data.NOTICE
import com.example.crc_android.data.NoticeToken
import com.example.crc_android.databinding.FragmentViewmoreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NoticeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notice, container, false)
        viewManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, true)
        viewAdapter = MyAdapter(requireActivity(), Data.dataList)


        view.findViewById<ImageView>(R.id.backarrow).setOnClickListener {
            findNavController().navigate(R.id.action_noticeFragment_to_viewmoreFragment)
        }

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_main).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter



        }
        return view
    }



}
