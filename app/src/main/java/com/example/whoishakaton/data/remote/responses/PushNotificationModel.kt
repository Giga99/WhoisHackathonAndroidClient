package com.example.whoishakaton.data.remote.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PushNotificationRequest(
    val data: NotificationData,
    val to: String
)

@JsonClass(generateAdapter = true)
data class NotificationData(
    val title: String,
    val message: String
)
