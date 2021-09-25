/*
 * Copyright (C) 2019 LotusFlare
 * All Rights Reserved.
 * Unauthorized copying and distribution of this file, via any medium is strictly prohibited.
 */

package com.example.whoishakaton.utils.overlays_and_dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

abstract class AutoDismissDialogFragment : DialogFragment() {

    private val mainHandler = Handler(Looper.getMainLooper())

    private var shouldDismiss: Boolean = false

    var onDismissedListener: OnDismissCallBack? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = arguments?.getBoolean(IS_CANCELABLE_KEY) ?: false
        shouldDismiss = savedInstanceState?.getBoolean(BUNDLE_SHOULD_DISMISS) ?: false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(BUNDLE_SHOULD_DISMISS, true)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    fun showDialogFor(fragmentManager: FragmentManager, milliseconds: Long) {
        showDialog(fragmentManager)
        mainHandler.postDelayed({ this.dismiss() }, milliseconds)
    }

    fun showDialog(fragmentManager: FragmentManager) {
        this.show(fragmentManager, dialogTag())
    }

    override fun onResume() {
        super.onResume()
        if (shouldDismiss) {
            dismiss()
        }
    }

    override fun dismiss() {
        if (isResumed) {
            shouldDismiss = false
            super.dismiss()
        } else {
            shouldDismiss = true
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissedListener?.invoke()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDismissedListener = null
        mainHandler.removeCallbacksAndMessages(null)
    }

    protected abstract fun dialogTag(): String

    companion object {
        private const val BUNDLE_SHOULD_DISMISS = "BUNDLE_SHOULD_DISMISS_KEY"

        const val IS_CANCELABLE_KEY = "is_cancelable"
    }
}

typealias OnDismissCallBack = () -> Unit
