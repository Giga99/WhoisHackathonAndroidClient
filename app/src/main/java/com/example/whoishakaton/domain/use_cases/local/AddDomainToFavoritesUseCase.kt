package com.example.whoishakaton.domain.use_cases.local

import com.example.whoishakaton.data.local.repositories.DomainRepository
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.domain.models.fromUIModelToEntity
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class AddDomainToFavoritesUseCase @Inject constructor(
    private val domainRepository: DomainRepository
) {

    suspend fun execute(domainInformationUIModel: DomainInformationUIModel): Resource<Boolean> =
        try {
            val domain = domainInformationUIModel.fromUIModelToEntity()
            val entity = domainRepository.getDomainById(domainInformationUIModel.id)

            if (entity == null)
                domainRepository.addDomainToFavorites(domain)
            else
                domainRepository.removeDomainFromFavorites(domain)
            Resource.Success(entity == null)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
}

