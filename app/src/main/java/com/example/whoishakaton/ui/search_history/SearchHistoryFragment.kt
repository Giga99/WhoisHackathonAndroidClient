package com.example.whoishakaton.ui.search_history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.whoishakaton.databinding.FragmentSearchHistoryBinding
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchHistoryFragment : ViewBindingFragment<FragmentSearchHistoryBinding>({
    FragmentSearchHistoryBinding.inflate(it)
}) {

    val searchHistoryViewModel: SearchHistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            val adapter = SearchHistoryRecyclerViewAdapter {

            }
            rvRecentSearches.adapter = adapter

            searchHistoryViewModel.searchHistory.observe(viewLifecycleOwner, { result ->
                when (result) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        adapter.submitList(result.data)
                    }

                    is Resource.Failure -> {
                        println(result.throwable)
                    }
                }
            })
        }
    }
}
