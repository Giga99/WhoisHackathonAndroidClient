package com.example.whoishakaton.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.whoishakaton.databinding.FragmentSearchBinding
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : ViewBindingFragment<FragmentSearchBinding>({
    FragmentSearchBinding.inflate(it)
}) {

    private val searchViewModel: SearchViewModel by viewModels()

    private val searchFragmentArgs: SearchFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.search(searchFragmentArgs.domainName)

        with(viewBinding) {
            searchViewModel.searchDomain.observe(viewLifecycleOwner, { result ->
                if (result is Resource.Success) {
                    tvDomainName.text = result.data.name
                    tvDomainExpiredDate.text = result.data.expirationDate
                    tvDomainAddress.text = result.data.address
                    tvDomainRegistrantName.text = result.data.registrantName
                } else if (result is Resource.Failure) {
                    println(result.throwable)
                }
            })
        }
    }
}
