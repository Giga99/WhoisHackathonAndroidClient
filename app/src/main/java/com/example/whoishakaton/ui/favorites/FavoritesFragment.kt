package com.example.whoishakaton.ui.favorites

import android.os.Bundle
import android.view.View
import com.example.whoishakaton.databinding.FragmentFavoritesBinding
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : ViewBindingFragment<FragmentFavoritesBinding>({
    FragmentFavoritesBinding.inflate(it)
}) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
