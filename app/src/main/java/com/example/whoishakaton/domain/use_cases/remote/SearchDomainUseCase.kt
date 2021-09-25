package com.example.whoishakaton.domain.use_cases.remote

import com.example.whoishakaton.data.remote.repositories.WhoisRepository
import com.example.whoishakaton.data.remote.responses.SearchDomainRequest
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.domain.models.fromResponseToUIModel
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class SearchDomainUseCase @Inject constructor(
    private val whoisRepository: WhoisRepository
) {

    suspend fun execute(domainRequest: SearchDomainRequest): Resource<DomainInformationUIModel> =
        try {
            Resource.Success(whoisRepository.searchDomain(domainRequest).fromResponseToUIModel())
        } catch (t: Throwable) {
            Resource.Failure(t)
        }
}
