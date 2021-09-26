package com.example.whoishakaton.ui.webview_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.whoishakaton.R
import com.example.whoishakaton.databinding.FragmentWebviewBinding
import com.example.whoishakaton.ui.LanguageViewModel
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : ViewBindingFragment<FragmentWebviewBinding>({
    FragmentWebviewBinding.inflate(it)
}) {

    private val languageViewModel: LanguageViewModel by activityViewModels()
    private val webViewFragmentArgs: WebViewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            languageViewModel.context.observe(viewLifecycleOwner, {
                tvWebviewFragmentTitle.text = it.resources.getString(R.string.rent_domain)
            })

            wvRentDomain.loadUrl(webViewFragmentArgs.url)

            ivBack.setOnClickListener { findNavController().navigateUp() }
        }
    }
}
