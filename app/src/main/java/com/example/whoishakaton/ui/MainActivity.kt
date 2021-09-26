package com.example.whoishakaton.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.whoishakaton.R
import com.example.whoishakaton.data.remote.FirebaseRetrofitInstance
import com.example.whoishakaton.data.remote.responses.NotificationData
import com.example.whoishakaton.data.remote.responses.PushNotificationRequest
import com.example.whoishakaton.databinding.ActivityMainBinding
import com.example.whoishakaton.utils.TOPIC
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandlerProvider
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsViewModel
import com.example.whoishakaton.utils.view_binding.Activity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainActivity : Activity<ActivityMainBinding>({
    ActivityMainBinding.inflate(it)
}) {

    private val generalEventsViewModel: GeneralEventsViewModel by viewModels()
    private val languageViewModel: LanguageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        languageViewModel.language.observe(this, {
            val locale = Locale(it)
            val conf = resources.configuration
            conf.setLocale(locale)
            languageViewModel.setContext(this.createConfigurationContext(conf))
        })

        GeneralEventsHandlerProvider.setHandler(generalEventsViewModel)
        generalEventsViewModel.handleDialogsAndOverlaysInActivity(this)

        with(activityBinding) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fcv_nav_host_fragment) as NavHostFragment
            bottomNavigation.setupWithNavController(navHostFragment.navController)

            navHostFragment.findNavController()
                .addOnDestinationChangedListener { _, destination, _ ->
                    when (destination.id) {
                        R.id.homeFragment, R.id.favoritesFragment, R.id.searchHistoryFragment ->
                            clBottomNav.visibility = View.VISIBLE
                        else -> clBottomNav.visibility = View.GONE
                    }
                }
        }
    }
}

class Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            val title = intent.getStringExtra("title")
            val description = intent.getStringExtra("description")
            if (title?.isNotEmpty() == true && description?.isNotEmpty() == true) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        FirebaseRetrofitInstance.api.postNotification(
                            PushNotificationRequest(
                                NotificationData(title, description),
                                TOPIC
                            )
                        )
                    } catch (e: Exception) {
                        println(e)
                    }
                }
            }
        }
    }
}
