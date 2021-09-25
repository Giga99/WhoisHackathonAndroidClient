package com.example.whoishakaton.ui.search_history

import android.os.Bundle
import android.view.View
import com.example.whoishakaton.databinding.FragmentSearchHistoryBinding
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchHistoryFragment : ViewBindingFragment<FragmentSearchHistoryBinding>({
    FragmentSearchHistoryBinding.inflate(it)
}) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
