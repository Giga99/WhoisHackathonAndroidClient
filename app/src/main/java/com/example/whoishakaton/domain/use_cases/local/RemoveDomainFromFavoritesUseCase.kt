package com.example.whoishakaton.domain.use_cases.local

import com.example.whoishakaton.data.local.repositories.DomainRepository
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.domain.models.fromUIModelToEntity
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class RemoveDomainFromFavoritesUseCase @Inject constructor(
    private val domainRepository: DomainRepository
) {

    suspend fun execute(domainInformationUIModel: DomainInformationUIModel): Resource<Unit> =
        try {
            domainRepository.removeDomainFromFavorites(domainInformationUIModel.fromUIModelToEntity())
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
}

