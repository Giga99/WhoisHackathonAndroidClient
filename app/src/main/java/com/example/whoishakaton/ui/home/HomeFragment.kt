package com.example.whoishakaton.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.whoishakaton.R
import com.example.whoishakaton.databinding.FragmentHomeBinding
import com.example.whoishakaton.ui.LanguageViewModel
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
    private val languageViewModel: LanguageViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            languageViewModel.context.observe(viewLifecycleOwner, {
                tvLanguage.text = it.resources.getString(R.string.language)
                tvHomeFragmentTitle.text = it.resources.getString(R.string.home_fragment_title)
                etSearch.hint = it.resources.getString(R.string.search_bar_hint)
                btnSearchText.text = it.resources.getString(R.string.search_bar_button_text)
                btnFeelingLucky.text = it.resources.getString(R.string.feeling_lucky)
                tvMostSearchedTitle.text = it.resources.getString(R.string.most_searched_title)
            })

            tvLanguage.setOnClickListener {
                languageViewModel.toggleLanguage()
            }

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

            llSearch.setOnClickListener {
                if (etSearch.text.isNotBlank())
                    findNavController().safeNavigate(
                        HomeFragmentDirections.actionHomeFragmentToSearchFragment(
                            etSearch.text.toString()
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
                                btnFeelingLucky.isVisible = result.data.isEmpty()
                            }

                            is Failure -> {
                                llRecentSearches.visibility = View.GONE
                                btnFeelingLucky.visibility = View.VISIBLE
                                println(result.throwable)
                            }
                        }
                    })
                } else {
                    llRecentSearches.visibility = View.GONE
                    btnFeelingLucky.visibility = View.VISIBLE
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
