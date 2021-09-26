package com.example.whoishakaton.utils.overlays_and_dialogs

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoishakaton.utils.OneTimeEvent
import com.example.whoishakaton.utils.exhaustive
import com.example.whoishakaton.utils.overlays_and_dialogs.OverlayOrDialog.*
import com.example.whoishakaton.utils.overlays_and_dialogs.OverlayOrDialog.Overlay.Loading
import com.example.whoishakaton.utils.postOneTimeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class GeneralEventsViewModel @Inject constructor() : ViewModel(), GeneralEventsHandler {

    private val _overlaysAndDialogs = MutableLiveData<OneTimeEvent<OverlayOrDialog>>()
    private val overlaysAndDialogs: LiveData<OneTimeEvent<OverlayOrDialog>> get() = _overlaysAndDialogs

    private var displayedOverlay: AutoDismissDialogFragment? = null
    private var displayedDialog: AlertDialog? = null
    private var displayedFragmentDialog: DialogFragment? = null

    @Volatile
    private var numOfLoading = 0

    private val loadingMutex = Mutex()

    override fun handleOverlay(overlay: Overlay) {
        _overlaysAndDialogs.postOneTimeEvent(overlay)
    }

    override fun dismiss() {
        _overlaysAndDialogs.postOneTimeEvent(Dismiss)
    }

    fun handleDialogsAndOverlaysInActivity(
        activity: FragmentActivity
    ) {
        overlaysAndDialogs.observe(activity, {
            it.handleEvent { overlayOrDialog ->
                viewModelScope.launch(Dispatchers.Main + NonCancellable) {
                    if (activity.isFinishing ||
                        activity.supportFragmentManager.isStateSaved ||
                        activity.supportFragmentManager.isDestroyed
                    ) return@launch

                    if (displayedOverlay != null || overlayOrDialog != DismissOverlay)
                        tryDismissDisplayedOverlayAndDialog()

                    when (overlayOrDialog) {

                        is Overlay -> {
                            when (overlayOrDialog) {
                                is Loading -> displayLoadingOverlay(activity)
                            }
                        }

                        is Dismiss -> loadingMutex.withLock { numOfLoading = 0 }

                        is DismissOverlay -> loadingMutex.withLock { numOfLoading = 0 }
                    }.exhaustive
                }
            }
        })

    }

    private suspend fun tryDismissDisplayedOverlayAndDialog() {
        loadingMutex.withLock {
            displayedOverlay?.dismiss()
            displayedOverlay = null

            displayedFragmentDialog?.dismiss()
            displayedFragmentDialog = null

            displayedDialog?.dismiss()
            displayedDialog = null
        }
    }

    override suspend fun startLoading() {
        loadingMutex.withLock {
            numOfLoading++
            if (numOfLoading == 1) {
                _overlaysAndDialogs.postOneTimeEvent(Loading)
            }
        }
    }

    override suspend fun endLoading() {
        loadingMutex.withLock {
            numOfLoading--
            numOfLoading = if (numOfLoading < 0) 0 else numOfLoading
            if (numOfLoading == 0) {
                _overlaysAndDialogs.postOneTimeEvent(DismissOverlay)
            }
        }
    }

    private fun displayLoadingOverlay(activity: FragmentActivity) {
        displayedOverlay = LoadingOverlay().also {
            it.showDialog(activity.supportFragmentManager)
        }
    }
}

