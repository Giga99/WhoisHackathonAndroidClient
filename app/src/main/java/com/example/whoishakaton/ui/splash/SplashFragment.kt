package com.example.whoishakaton.ui.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.whoishakaton.databinding.FragmentSplashBinding
import com.example.whoishakaton.utils.safeNavigate
import com.example.whoishakaton.utils.view_binding.MainHandler
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : ViewBindingFragment<FragmentSplashBinding>({
    FragmentSplashBinding.inflate(it)
}) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainHandler.post {
            findNavController().safeNavigate(SplashFragmentDirections.actionSplashFragmentToNavigationMain())
        }
    }
}
