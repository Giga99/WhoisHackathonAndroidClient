package com.example.whoishakaton.domain.use_cases.remote

import com.example.whoishakaton.data.remote.repositories.WhoisRepository
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class RequestNotificationByEmailUseCase @Inject constructor(
    private val whoisRepository: WhoisRepository
) {

    suspend fun execute(domain: String, email: String): Resource<Unit> =
        try {
            Resource.Success(whoisRepository.requestNotificationByEmail(domain, email))
        } catch (t: Throwable) {
            Resource.Failure(t)
        }
}
