package com.example.whoishakaton.ui.home

import android.os.Bundle
import android.view.View
import com.example.whoishakaton.databinding.FragmentHomeBinding
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ViewBindingFragment<FragmentHomeBinding>({
    FragmentHomeBinding.inflate(it)
}) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
