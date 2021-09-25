package com.example.whoishakaton.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.whoishakaton.databinding.FragmentHomeBinding
import com.example.whoishakaton.utils.Resource.*
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ViewBindingFragment<FragmentHomeBinding>({
    FragmentHomeBinding.inflate(it)
}) {

    //    private val searchViewModel: SearchViewModel by hiltNavGraphViewModels(R.id.navigation_main)
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            val adapter = RecentSearchesHomeRecyclerViewAdapter {

            }
            rvRecentSearches.adapter = adapter

            etSearch.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    searchViewModel.recentSearches.observe(viewLifecycleOwner, { result ->
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
                searchViewModel.search(v.text.toString())
                true
            }

            searchViewModel.searchDomain.observe(viewLifecycleOwner, { result ->
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
