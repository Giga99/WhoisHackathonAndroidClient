package com.example.whoishakaton.domain.use_cases.local

import com.example.whoishakaton.data.local.repositories.DomainHistoryRepository
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.domain.models.fromUIModelToEntity
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class AddNewSearchUseCase @Inject constructor(
    private val domainHistoryRepository: DomainHistoryRepository
) {

    suspend fun execute(domain: DomainHistoryUIModel): Resource<Unit> =
        try {
            domainHistoryRepository.addDomainToSearchHistory(domain.fromUIModelToEntity())
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
}

