package com.example.whoishakaton.ui

import android.os.Bundle
import com.example.whoishakaton.databinding.ActivityMainBinding
import com.example.whoishakaton.utils.view_binding.Activity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : Activity<ActivityMainBinding>({
    ActivityMainBinding.inflate(it)
}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
