package com.example.whoishakaton.ui.search

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.whoishakaton.R
import com.example.whoishakaton.databinding.ChooseWayToNotifyDialogBinding
import com.example.whoishakaton.databinding.FragmentSearchBinding
import com.example.whoishakaton.ui.Receiver
import com.example.whoishakaton.ui.search.SearchViewModel.AddRemoveFavoriteResult.FailedResult
import com.example.whoishakaton.ui.search.SearchViewModel.AddRemoveFavoriteResult.SuccessfulResult
import com.example.whoishakaton.utils.*
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

        with(viewBinding) {
            if (searchFragmentArgs.domainName == RANDOM) {
                clContent.visibility = View.GONE
                lavDices.visibility = View.VISIBLE
                searchViewModel.getRandomDomain()
            } else {
                searchViewModel.search(searchFragmentArgs.domainName)
            }

            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }

            ivFavoriteDomain.setOnClickListener {
                searchViewModel.addRemoveFavorite()
            }

            btnSetAlarm.setOnClickListener {
                showWayToNotifyDialog()
            }

            btnRentDomain.setOnClickListener {
                if (searchFragmentArgs.domainName.contains(".com")) {
                    findNavController().safeNavigate(
                        SearchFragmentDirections.actionSearchFragmentToWebViewFragment(
                            NAMECHEAP_URL
                        )
                    )
                } else if (searchFragmentArgs.domainName.contains(".rs")) {
                    findNavController().safeNavigate(
                        SearchFragmentDirections.actionSearchFragmentToWebViewFragment(
                            SUPERHOSTING_URL
                        )
                    )
                } else if (searchFragmentArgs.domainName.contains(".ru")) {
                    findNavController().safeNavigate(
                        SearchFragmentDirections.actionSearchFragmentToWebViewFragment(
                            NIC_URL
                        )
                    )
                } else if (searchFragmentArgs.domainName.contains(".mk")) {
                    findNavController().safeNavigate(
                        SearchFragmentDirections.actionSearchFragmentToWebViewFragment(
                            MKHOST_URL
                        )
                    )
                } else if (searchFragmentArgs.domainName.contains(".org")) {
                    findNavController().safeNavigate(
                        SearchFragmentDirections.actionSearchFragmentToWebViewFragment(
                            GODADDY_URL
                        )
                    )
                } else if (searchFragmentArgs.domainName.contains(".net")) {
                    findNavController().safeNavigate(
                        SearchFragmentDirections.actionSearchFragmentToWebViewFragment(
                            DOMAIN_URL
                        )
                    )
                } else if (searchFragmentArgs.domainName.contains(".uk")) {
                    findNavController().safeNavigate(
                        SearchFragmentDirections.actionSearchFragmentToWebViewFragment(
                            NAMES_URL
                        )
                    )
                } else if (searchFragmentArgs.domainName.contains(".se")) {
                    findNavController().safeNavigate(
                        SearchFragmentDirections.actionSearchFragmentToWebViewFragment(
                            DOMAIN101_URL
                        )
                    )
                }
            }

            searchViewModel.searchDomain.observe(viewLifecycleOwner, { result ->
                clContent.visibility = View.VISIBLE
                lavDices.visibility = View.GONE
                if (result is Resource.Success) {
                    with(result.data) {
                        tvDomainName.text = name

                        expirationDateInMiliseconds?.let {
                            svDomainInfo.visibility = View.VISIBLE
                            tvExpiredDate.visibility = View.VISIBLE
                            tvExpiredDate.text = it.getFormattedDateForMilliseconds()

                            createdDateInMiliseconds?.let {
                                tvCreatedDate.visibility = View.VISIBLE
                                tvCreatedDate.text = it.getFormattedDateForMilliseconds()
                            }

                            address?.let {
                                tvAddress.visibility = View.VISIBLE
                                tvAddress.text = it
                            }

                            registarName?.let {
                                tvRegistrantName.visibility = View.VISIBLE
                                tvRegistrantName.text = it
                            }

                            whoIsResponse?.let {
                                tvWhoisResponse.visibility = View.VISIBLE
                                tvWhoisResponse.text = it
                            }
                        } ?: kotlin.run {
                            clAvailableDomain.visibility = View.VISIBLE
                        }
                    }
                } else if (result is Resource.Failure) {
                    println(result.throwable)
                }
            })

            searchViewModel.addRemoveFavorite.observe(viewLifecycleOwner, {
                it.handleEvent { result ->
                    if (result is SuccessfulResult) {
                        if (result.add) ivFavoriteDomain.setImageResource(R.drawable.ic_star)
                        else ivFavoriteDomain.setImageResource(R.drawable.ic_outline_star)
                    } else if (result is FailedResult) {
                        println(result.throwable)
                    }
                }
            })
        }
    }

    fun showWayToNotifyDialog() {
        val binding =
            ChooseWayToNotifyDialogBinding.inflate(LayoutInflater.from(requireContext()))
        val dialog = CustomAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .create()

        with(binding) {
            btnNotification.setOnClickListener {
                val alarmManager =
                    requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager

                val intent = Intent(requireContext(), Receiver::class.java)
                intent.putExtra(
                    "title",
                    getString(R.string.alarm_domain_expires_title, searchViewModel.domain.name)
                )

                intent.putExtra(
                    "description",
                    getString(
                        R.string.alarm_domain_expires_description
                    )
                )

                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )

                searchViewModel.domain.expirationDateInMiliseconds?.toLong()?.let {
                    alarmManager.set(
                        AlarmManager.RTC_WAKEUP,
                        it,
                        pendingIntent
                    )

                    searchViewModel.addFavorite()
                }

                dialog.dismiss()
            }

            btnEmail.setOnClickListener {
                findNavController().safeNavigate(
                    SearchFragmentDirections.actionSearchFragmentToEnterEmailFragment(
                        searchFragmentArgs.domainName
                    )
                )

                dialog.dismiss()
            }
        }

        dialog.show()
    }
}
