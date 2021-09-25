package com.example.whoishakaton.domain.use_cases.local

import com.example.whoishakaton.data.local.repositories.DomainRepository
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.domain.models.fromEntityToUIModel
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class GetFavoriteDomainsUseCase @Inject constructor(
    private val domainRepository: DomainRepository
) {

    suspend fun execute(): Resource<List<DomainInformationUIModel>> =
        try {
            Resource.Success(
                domainRepository.getAllFavoriteDomains().map { it.fromEntityToUIModel() }
            )
        } catch (e: Exception) {
            Resource.Failure(e)
        }
}

