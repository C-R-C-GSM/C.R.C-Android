package com.example.crc_android

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.crc_android.base.UtilityBase
import com.example.crc_android.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : UtilityBase.BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    val TAG = "MainActivity"
    lateinit var navi: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navi = binding.bottomNavigationView
        //NavController 생성
        navController = findNavController(R.id.navHostFragment)
        //앱 바 구성성
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,

            )
        )

        initNavigation()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navi.setupWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp() || super.onSupportNavigateUp()
        setContentView(R.layout.activity_main)

    }

    private fun initNavigation() {

        val navController = findNavController(R.id.navHostFragment)
        navi.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            // fragment id 가 아닐 시 bottom navigation 안뜸
            if ( destination.id == R.id.homeFragment) {
                navi.visibility = View.VISIBLE
            } else {
                navi.visibility = View.GONE
            }
        }
    }





}

