package com.example.whoishakaton.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.whoishakaton.databinding.FragmentHomeBinding
import com.example.whoishakaton.utils.RANDOM
import com.example.whoishakaton.utils.Resource.Failure
import com.example.whoishakaton.utils.Resource.Success
import com.example.whoishakaton.utils.safeNavigate
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ViewBindingFragment<FragmentHomeBinding>({
    FragmentHomeBinding.inflate(it)
}) {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            val adapter = RecentSearchesHomeRecyclerViewAdapter {
                findNavController().safeNavigate(
                    HomeFragmentDirections.actionHomeFragmentToSearchFragment(
                        it.title
                    )
                )
            }
            rvRecentSearches.adapter = adapter

            val popularDomainsAdapter = PopularDomainsRecyclerViewAdapter {
                findNavController().safeNavigate(
                    HomeFragmentDirections.actionHomeFragmentToSearchFragment(
                        it.name
                    )
                )
            }
            rvMostSearched.adapter = popularDomainsAdapter

            btnFeelingLucky.setOnClickListener {
                findNavController().safeNavigate(
                    HomeFragmentDirections.actionHomeFragmentToSearchFragment(
                        RANDOM
                    )
                )
            }

            homeViewModel.popularDomains.observe(viewLifecycleOwner, { result ->
                if (result is Success) {
                    popularDomainsAdapter.submitList(result.data)
                } else if (result is Failure) {
                    println(result.throwable)
                }
            })

            etSearch.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    homeViewModel.recentSearches.observe(viewLifecycleOwner, { result ->
                        when (result) {
                            is Success -> {
                                adapter.submitList(result.data)
                                llRecentSearches.isVisible = result.data.isNotEmpty()
                            }

                            is Failure -> {
                                llRecentSearches.visibility = View.GONE
                                println(result.throwable)
                            }
                        }
                    })
                } else {
                    llRecentSearches.visibility = View.GONE
                }
            }

            etSearch.setOnEditorActionListener { v, _, _ ->
                findNavController().safeNavigate(
                    HomeFragmentDirections.actionHomeFragmentToSearchFragment(
                        v.text.toString()
                    )
                )
                true
            }
        }
    }
}
