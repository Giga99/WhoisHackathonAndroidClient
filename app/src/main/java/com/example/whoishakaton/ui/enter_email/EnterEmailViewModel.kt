package com.example.whoishakaton.ui.enter_email

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoishakaton.domain.use_cases.remote.RequestNotificationByEmailUseCase
import com.example.whoishakaton.utils.Resource
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandler
import com.example.whoishakaton.utils.overlays_and_dialogs.GeneralEventsHandlerProvider
import com.example.whoishakaton.utils.overlays_and_dialogs.launchWithLoadingOverlay
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnterEmailViewModel @Inject constructor(
    private val requestNotificationByEmailUseCase: RequestNotificationByEmailUseCase
) : ViewModel() {

    private val handler: GeneralEventsHandler = GeneralEventsHandlerProvider.generalEventsHandler

    private val _requestNotification = MutableLiveData<Resource<Unit>>()
    val requestNotification: LiveData<Resource<Unit>> = _requestNotification

    fun requestNotificationByEmail(domain: String, email: String) =
        viewModelScope.launchWithLoadingOverlay(handler) {
            _requestNotification.value = requestNotificationByEmailUseCase.execute(domain, email)
        }
}
