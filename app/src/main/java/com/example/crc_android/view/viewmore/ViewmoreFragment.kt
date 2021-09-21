package com.example.crc_android.view.viewmore

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.crc_android.MainActivity
import com.example.crc_android.R
import com.example.crc_android.databinding.FragmentViewmoreBinding


class ViewmoreFragment : Fragment() {


    private lateinit var binding: FragmentViewmoreBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_viewmore, container, false)

        (requireActivity() as MainActivity)
        view.findViewById<Button>(R.id.noticeBtn).setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, AdminnoticeFragment()).commit()
        }
        return view
    }
}