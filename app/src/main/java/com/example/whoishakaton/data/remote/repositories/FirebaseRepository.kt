package com.example.whoishakaton.data.remote.repositories

import com.example.whoishakaton.data.remote.FirebaseRetrofit
import com.example.whoishakaton.data.remote.responses.PushNotificationRequest
import com.example.whoishakaton.utils.FIREBASE_SERVER
import javax.inject.Inject
import javax.inject.Named

class FirebaseRepository @Inject constructor(
    @Named(FIREBASE_SERVER) private val firebaseRetrofit: FirebaseRetrofit
) {

    suspend fun postNotification(notificationModel: PushNotificationRequest) =
        firebaseRetrofit.postNotification(notificationModel)
}
