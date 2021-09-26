package com.example.whoishakaton.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.getFormattedDateForMilliseconds(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
}
