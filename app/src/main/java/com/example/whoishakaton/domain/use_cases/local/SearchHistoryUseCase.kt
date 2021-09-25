package com.example.whoishakaton.domain.use_cases.local

import com.example.whoishakaton.data.local.repositories.DomainHistoryRepository
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.domain.models.fromEntityToUIModel
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class SearchHistoryUseCase @Inject constructor(
    private val domainHistoryRepository: DomainHistoryRepository
) {

    suspend fun execute(): Resource<List<DomainHistoryUIModel>> =
        try {
            Resource.Success(
                domainHistoryRepository.getSearchHistory()
                    .map { it.fromEntityToUIModel() }
                    .sortedBy { it.date }
                    .reversed()
            )
        } catch (e: Exception) {
            Resource.Failure(e)
        }
}
