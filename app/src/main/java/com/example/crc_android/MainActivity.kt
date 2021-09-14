package com.example.crc_android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
    lateinit var navi: BottomNavigationView
    private lateinit var navController: NavController
    override fun ActivityMainBinding.onCreate() {
        navi = binding.bottomNavigationView

        setSupportActionBar(toolbar)
        navController = findNavController(R.id.navHostFragment)
        //앱 바 구성성
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.reviewFragment,
                R.id.friendFragment
            )
        )

        initNavigation()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navi.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initNavigation() {

        val navController = findNavController(R.id.navHostFragment)
        navi.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            // fragment id 가 아닐 시 bottom navigation 안뜸
            if (destination.id == R.id.homeFragment || destination.id == R.id.reviewFragment || destination.id == R.id.friendFragment) {

                navi.visibility = View.VISIBLE
            } else {
                navi.visibility = View.GONE
            }
        }
    }




}
