package com.example.movie.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

sealed class UtilityBase {
    open class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) : Fragment() {

        lateinit var binding: T

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
            binding.onCreateView()
            binding.onViewCreated()
            return binding.root
        }

        open fun T.onCreateView() = Unit
        open fun T.onViewCreated() = Unit
    }

    open class  BaseActivity<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) :
        AppCompatActivity() {

        lateinit var binding: T

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, layoutRes)
            binding.onCreate()
            binding.lifecycleOwner = this
        }

        open fun T.onCreate() = Unit

    }
}