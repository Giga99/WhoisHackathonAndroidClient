package com.example.whoishakaton.data.local.repositories

import com.example.whoishakaton.data.local.db.DomainHistoryDAO
import com.example.whoishakaton.domain.repositories.DomainHistoryRepository
import javax.inject.Inject

class DomainHistoryRepositoryImpl @Inject constructor(
    val domainHistoryDAO: DomainHistoryDAO
): DomainHistoryRepository {

}
