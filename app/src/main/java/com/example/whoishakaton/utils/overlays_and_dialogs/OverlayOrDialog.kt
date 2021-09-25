package com.example.whoishakaton.utils.overlays_and_dialogs

sealed class OverlayOrDialog {

    sealed class Overlay : OverlayOrDialog() {
        object Loading : Overlay()
    }

    object Dismiss : OverlayOrDialog()

    object DismissOverlay : OverlayOrDialog()
}
