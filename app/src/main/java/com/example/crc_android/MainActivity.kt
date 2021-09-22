package com.example.crc_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.crc_android.databinding.ActivityMainBinding
import com.example.crc_android.view.friend.FriendFragment
import com.example.crc_android.view.home.HomeFragment
import com.example.crc_android.view.review.ReviewFragment
import com.example.crc_android.view.viewmore.ViewmoreFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navi: BottomNavigationView
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.hide()

        navi = binding.bottomNavigationView

        initView()
        supportFragmentManager.beginTransaction().replace(R.id.navHostFragment, HomeFragment()).commit()
        navi.selectedItemId = R.id.homeFragment
    }

    fun initView(){
        navi.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction().replace(R.id.navHostFragment, HomeFragment()).commit()
                    true
                }
                R.id.friendFragment -> {
                    supportFragmentManager.beginTransaction().replace(R.id.navHostFragment, FriendFragment()).commit()
                    true
                }
                R.id.reviewFragment -> {
                    supportFragmentManager.beginTransaction().replace(R.id.navHostFragment, ReviewFragment()).commit()
                    true
                }
                R.id.viewmoreFragment -> {
                    supportFragmentManager.beginTransaction().replace(R.id.navHostFragment, ViewmoreFragment()).commit()
                    true
                }
                else -> false
            }

        }
    }
}