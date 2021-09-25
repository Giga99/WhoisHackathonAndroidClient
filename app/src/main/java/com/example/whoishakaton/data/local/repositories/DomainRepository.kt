package com.example.whoishakaton.data.local.repositories

import com.example.whoishakaton.data.local.db.DomainDAO
import com.example.whoishakaton.data.local.db.entities.DomainEntity
import javax.inject.Inject

class DomainRepository @Inject constructor(
    private val domainDAO: DomainDAO
) {

    suspend fun addDomainToFavorites(domain: DomainEntity) = domainDAO.upsert(domain)

    suspend fun removeDomainFromFavorites(domain: DomainEntity) = domainDAO.delete(domain)

    suspend fun getAllFavoriteDomains() = domainDAO.getAllDomains()
}
