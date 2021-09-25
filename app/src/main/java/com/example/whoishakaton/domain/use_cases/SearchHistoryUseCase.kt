package com.example.whoishakaton.domain.use_cases

import com.example.whoishakaton.data.local.repositories.DomainHistoryRepository
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.domain.models.fromEntityToUIModel
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class SearchHistoryUseCase @Inject constructor(
    private val domainHistoryRepository: DomainHistoryRepository
) {

    suspend fun execute(): Resource<List<DomainHistoryUIModel>> {
        return try {
            val list = domainHistoryRepository.getSearchHistory()
            Resource.Success(list.map { it.fromEntityToUIModel() }.sortedBy { it.date }.reversed())
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}
