package com.example.whoishakaton.ui.search

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.whoishakaton.R
import com.example.whoishakaton.databinding.FragmentSearchBinding
import com.example.whoishakaton.ui.Receiver
import com.example.whoishakaton.ui.search.SearchViewModel.AddRemoveFavoriteResult.FailedResult
import com.example.whoishakaton.ui.search.SearchViewModel.AddRemoveFavoriteResult.SuccessfulResult
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.view_binding.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SearchFragment : ViewBindingFragment<FragmentSearchBinding>({
    FragmentSearchBinding.inflate(it)
}) {

    private val searchViewModel: SearchViewModel by viewModels()

    private val searchFragmentArgs: SearchFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        searchViewModel.search(searchFragmentArgs.domainName)

        with(viewBinding) {
            ivFavoriteDomain.setOnClickListener {
                searchViewModel.addRemoveFavorite()
            }

            btnSetAlarm.setOnClickListener {
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

                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
//                    System.currentTimeMillis() + searchViewModel.domain.expirationDate,
                    System.currentTimeMillis() + 5000,
                    pendingIntent
                )

                searchViewModel.addFavorite()
            }

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
}
