package com.example.whoishakaton.utils

import android.content.Context
import android.view.Window
import androidx.appcompat.app.AlertDialog

open class CustomAlertDialogBuilder : AlertDialog.Builder {

    constructor(context: Context) : super(context)

    constructor(context: Context, themeResId: Int) : super(context, themeResId)

    override fun create(): AlertDialog {
        val dialog = super.create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }
}
