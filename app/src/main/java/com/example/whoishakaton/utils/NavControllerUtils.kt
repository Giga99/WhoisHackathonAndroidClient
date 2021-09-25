package com.example.whoishakaton.utils

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

/**
 * Extension function used to avoid crashes when two or more navigation events are triggered simultaneously.
 * The exception that the second nav event throws is caught in order to omit the event.
 */
fun NavController.safeNavigate(resId: Int, args: Bundle? = null, options: NavOptions? = null) {
    try {
        navigate(resId, args, options)
    } catch (e: IllegalArgumentException) {
        println(e)
    }
}

fun NavController.safeNavigate(directions: NavDirections) {
    safeNavigate(directions.actionId, directions.arguments)
}
