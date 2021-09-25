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
    private lateinit var navController: NavController
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.hide()

        navi = binding.bottomNavigationView
        navController = findNavController(R.id.navHostFragment)
        //앱 바 구성성
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.friendFragment,
                R.id.reviewFragment,
                R.id.suggestionFragment,
                R.id.viewmoreFragment,
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navi.setupWithNavController(navController)

    }



    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}