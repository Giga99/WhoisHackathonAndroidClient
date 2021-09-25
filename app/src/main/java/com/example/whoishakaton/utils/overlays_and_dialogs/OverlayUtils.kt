package com.example.whoishakaton.utils.overlays_and_dialogs

import kotlinx.coroutines.*

fun CoroutineScope.launchWithLoadingOverlay(
    handler: GeneralEventsHandler,
    lambda: suspend () -> Unit
) = launch {
    withContext(Dispatchers.Main + NonCancellable) {
        handler.startLoading()
        lambda.invoke()
        delay(150)
        handler.endLoading()
    }
}
