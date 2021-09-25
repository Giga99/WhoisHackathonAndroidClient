package com.example.whoishakaton.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.whoishakaton.R
import com.example.whoishakaton.databinding.FragmentHomeBinding
import com.example.whoishakaton.ui.search.SearchViewModel
import com.example.whoishakaton.utils.Resource.*
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ViewBindingFragment<FragmentHomeBinding>({
    FragmentHomeBinding.inflate(it)
}) {

    private val searchViewModel: SearchViewModel by hiltNavGraphViewModels(R.id.navigation_main)
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            val adapter = RecentSearchesHomeRecyclerViewAdapter {

            }
            rvRecentSearches.adapter = adapter

            etSearch.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    homeViewModel.recentSearches.observe(viewLifecycleOwner, { result ->
                        when (result) {
                            is Loading -> {

                            }

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
                if (v.text.isNotBlank()) homeViewModel.search(v.text.toString())
                true
            }

            homeViewModel.searchDomain.observe(viewLifecycleOwner, { result ->
                when (result) {
                    is Loading -> {

                    }

                    is Success -> {
                        println(result.data.title)
                    }

                    is Failure -> {
                        println(result.throwable)
                    }
                }
            })
        }
    }
}
