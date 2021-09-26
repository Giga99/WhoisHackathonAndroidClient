package com.example.whoishakaton.domain.use_cases.remote

import com.example.whoishakaton.data.remote.repositories.WhoisRepository
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.domain.models.fromResponseToUIModel
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class GetPopularDomainsUseCase @Inject constructor(
    private val whoisRepository: WhoisRepository
) {

    suspend fun execute(): Resource<List<DomainInformationUIModel>> =
        try {
            val list = whoisRepository.getPopularDomains().map { it.fromResponseToUIModel() }
            Resource.Success(list)
        } catch (t: Throwable) {
            Resource.Failure(t)
        }
}
