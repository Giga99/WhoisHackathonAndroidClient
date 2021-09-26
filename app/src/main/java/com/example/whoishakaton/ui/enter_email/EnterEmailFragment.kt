package com.example.whoishakaton.ui.enter_email

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.whoishakaton.R
import com.example.whoishakaton.databinding.FragmentEnterEmailBinding
import com.example.whoishakaton.ui.LanguageViewModel
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterEmailFragment : ViewBindingFragment<FragmentEnterEmailBinding>({
    FragmentEnterEmailBinding.inflate(it)
}) {

    private val languageViewModel: LanguageViewModel by activityViewModels()
    private val enterEmailViewModel: EnterEmailViewModel by viewModels()

    private val enterEmailFragmentArgs: EnterEmailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            languageViewModel.context.observe(viewLifecycleOwner, {
                tvEnterEmailTitle.text = it.resources.getString(R.string.enter_email)
                etEmail.hint = it.resources.getString(R.string.email)
                btnEnter.text = it.resources.getString(R.string.confirm)
            })

            tvDomainName.text = enterEmailFragmentArgs.domain

            ivBack.setOnClickListener { findNavController().navigateUp() }

            btnEnter.setOnClickListener {
                if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
                    Toast.makeText(requireContext(), "Unesite mejl!", Toast.LENGTH_SHORT).show()
                } else if (etEmail.text.isNotBlank()) {
                    enterEmailViewModel.requestNotificationByEmail(
                        enterEmailFragmentArgs.domain,
                        etEmail.text.toString()
                    )
                }
            }

            enterEmailViewModel.requestNotification.observe(viewLifecycleOwner, {
                if (it is Resource.Success) {
                    findNavController().navigateUp()
                } else if (it is Resource.Failure) {
                    println(it)
                }
            })
        }
    }
}
