package com.example.whoishakaton.utils

import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDateForMilliseconds(time: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
}
