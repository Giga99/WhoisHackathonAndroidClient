package com.example.whoishakaton.domain.use_cases.remote

import com.example.whoishakaton.data.remote.repositories.FirebaseRepository
import com.example.whoishakaton.data.remote.responses.PushNotificationRequest
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class PostNotificationUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend fun execute(pushNotificationRequest: PushNotificationRequest): Resource<Unit> =
        try {
            firebaseRepository.postNotification(pushNotificationRequest)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
}
