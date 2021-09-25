/*
 * Copyright (C) 2021 LotusFlare
 * All Rights Reserved.
 * Unauthorized copying and distribution of this file, via any medium is strictly prohibited.
 */

package com.example.whoishakaton.utils.overlays_and_dialogs

import kotlinx.coroutines.CompletableDeferred
import java.lang.ref.WeakReference

object GeneralEventsHandlerProvider {

    val generalEventsHandler: GeneralEventsHandler
        get() = generalEventsHandlerDelegator

    private val generalEventsHandlerDelegator: GeneralEventsHandlerDelegator =
        GeneralEventsHandlerDelegator()

    fun setHandler(generalEventsHandler: GeneralEventsHandler) {
        generalEventsHandlerDelegator.setValue(generalEventsHandler)
    }
}

/**
 * Sometimes [startLoading] will be call before [setValue], so coroutine has to be suspend while [generalEventsHandler] is not null.
 * You can reproduce this if you change <b>Background process limit</b> and choose <b>No background processes</b> from Developer options.
 */
class GeneralEventsHandlerDelegator : GeneralEventsHandler {

    private lateinit var isInitializedDeferred: CompletableDeferred<Boolean>

    var generalEventsHandler: WeakReference<GeneralEventsHandler>? = null

    override fun handleOverlay(overlay: OverlayOrDialog.Overlay) {
        generalEventsHandler?.get()?.handleOverlay(overlay)
    }

    override suspend fun startLoading() {
        if (generalEventsHandler?.get() == null)
            isInitializedDeferred = CompletableDeferred()

        isInitializedDeferred.await()
        generalEventsHandler?.get()?.startLoading()
    }

    override fun dismiss() {
        generalEventsHandler?.get()?.dismiss()
    }

    override suspend fun endLoading() {
        if (generalEventsHandler?.get() == null)
            isInitializedDeferred = CompletableDeferred()

        isInitializedDeferred.await()
        generalEventsHandler?.get()?.endLoading()
    }

//    override fun setBottomNavVisibility(bottomNavVisibility: BottomNavVisibility) {
//        generalEventsHandler?.get()?.setBottomNavVisibility(bottomNavVisibility)
//    }

    fun setValue(handler: GeneralEventsHandler) {
        if (generalEventsHandler?.get() == null)
            isInitializedDeferred = CompletableDeferred()

        this.generalEventsHandler = WeakReference(handler)
        isInitializedDeferred.complete(true)
    }
}
