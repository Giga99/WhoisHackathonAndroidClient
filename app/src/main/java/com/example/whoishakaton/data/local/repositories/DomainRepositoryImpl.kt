package com.example.whoishakaton.data.local.repositories

import com.example.whoishakaton.data.local.db.DomainDAO
import com.example.whoishakaton.data.local.db.entities.DomainEntity
import com.example.whoishakaton.domain.repositories.DomainRepository
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(
    private val domainDAO: DomainDAO
): DomainRepository {

    override suspend fun addDomainToFavorites(domain: DomainEntity) = domainDAO.upsert(domain)

    override suspend fun removeDomainFromFavorites(domain: DomainEntity) = domainDAO.delete(domain)

    override suspend fun getAllFavoriteDomains() = domainDAO.getAllDomains()
}
