package com.example.whoishakaton.data.local.repositories

import com.example.whoishakaton.data.local.db.DomainHistoryDAO
import com.example.whoishakaton.data.local.db.entities.DomainHistoryEntity
import javax.inject.Inject

class DomainHistoryRepository @Inject constructor(
    private val domainHistoryDAO: DomainHistoryDAO
) {

    suspend fun addDomainToSearchHistory(domain: DomainHistoryEntity): Long =
        domainHistoryDAO.upsert(domain)

    suspend fun removeDomainFromSearchHistory(domain: DomainHistoryEntity) =
        domainHistoryDAO.delete(domain)

    suspend fun getSearchHistory(): List<DomainHistoryEntity> =
        domainHistoryDAO.getAllSearchedDomains()
}
