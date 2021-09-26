package com.example.whoishakaton.utils.overlays_and_dialogs

import com.example.whoishakaton.utils.overlays_and_dialogs.OverlayOrDialog.Overlay

interface GeneralEventsHandler {

    fun handleOverlay(overlay: Overlay)

    suspend fun startLoading()

    fun dismiss()

    suspend fun endLoading()
}
