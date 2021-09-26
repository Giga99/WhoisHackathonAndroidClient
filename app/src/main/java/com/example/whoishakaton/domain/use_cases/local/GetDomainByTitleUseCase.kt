package com.example.whoishakaton.domain.use_cases.local

import com.example.whoishakaton.data.local.repositories.DomainRepository
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class GetDomainByTitleUseCase @Inject constructor(
    private val domainRepository: DomainRepository
) {

    suspend fun execute(title: String): Resource<Boolean> =
        try {
            Resource.Success(domainRepository.getDomainByTitle(title) != null)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
}
