package com.example.whoishakaton.data.local.repositories

import com.example.whoishakaton.data.local.db.DomainHistoryDAO
import javax.inject.Inject

class DomainHistoryRepository @Inject constructor(
    val domainHistoryDAO: DomainHistoryDAO
) {
}
