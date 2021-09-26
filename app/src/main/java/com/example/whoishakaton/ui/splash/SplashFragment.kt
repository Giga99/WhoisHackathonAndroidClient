package com.example.whoishakaton.ui.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.whoishakaton.databinding.FragmentSplashBinding
import com.example.whoishakaton.utils.safeNavigate
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : ViewBindingFragment<FragmentSplashBinding>({
    FragmentSplashBinding.inflate(it)
}) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2500)
            findNavController().safeNavigate(SplashFragmentDirections.actionSplashFragmentToNavigationMain())
        }
    }
}
