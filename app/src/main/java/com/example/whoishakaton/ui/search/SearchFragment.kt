package com.example.whoishakaton.ui.search

import android.os.Bundle
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.whoishakaton.R
import com.example.whoishakaton.databinding.FragmentSearchBinding
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : ViewBindingFragment<FragmentSearchBinding>({
    FragmentSearchBinding.inflate(it)
}) {

    private val searchViewModel: SearchViewModel by hiltNavGraphViewModels(R.id.navigation_main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
