package com.example.whoishakaton.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.whoishakaton.databinding.FragmentFavoritesBinding
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.safeNavigate
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : ViewBindingFragment<FragmentFavoritesBinding>({
    FragmentFavoritesBinding.inflate(it)
}) {

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            val adapter = FavoritesRecyclerViewAdapter {
                findNavController().safeNavigate(
                    FavoritesFragmentDirections.actionFavoritesFragmentToSearchFragment(
                        it.name
                    )
                )
            }
            rvFavorites.adapter = adapter

            ivRefreshDomains.setOnClickListener {
                favoritesViewModel.updateDomains()
            }

            favoritesViewModel.favoriteDomains.observe(viewLifecycleOwner, { result ->
                if (result is Resource.Success) {
                    adapter.submitList(result.data)
                } else if (result is Resource.Failure) {
                    println(result.throwable)
                }
            })
        }
    }
}
