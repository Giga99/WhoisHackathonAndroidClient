package com.example.whoishakaton.domain.use_cases

import com.example.whoishakaton.data.local.repositories.DomainHistoryRepository
import com.example.whoishakaton.domain.models.DomainHistoryUIModel
import com.example.whoishakaton.domain.models.fromEntityToUIModel
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class RecentSearchesHomeUseCase @Inject constructor(
    private val domainHistoryRepository: DomainHistoryRepository
) {

    suspend fun execute(): Resource<List<DomainHistoryUIModel>> {
        return try {
            val list = domainHistoryRepository.getSearchHistory()
            Resource.Success(list.map { it.fromEntityToUIModel() }.sortedBy { it.date }.reversed().take(5))
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}

