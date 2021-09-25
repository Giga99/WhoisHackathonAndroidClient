package com.example.whoishakaton.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.whoishakaton.R
import com.example.whoishakaton.databinding.ActivityMainBinding
import com.example.whoishakaton.utils.view_binding.Activity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : Activity<ActivityMainBinding>({
    ActivityMainBinding.inflate(it)
}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(activityBinding) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fcv_nav_host_fragment) as NavHostFragment
            bottomNavigation.setupWithNavController(navHostFragment.navController)
        }
    }
}
