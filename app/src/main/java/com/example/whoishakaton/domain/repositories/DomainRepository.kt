package com.example.whoishakaton.domain.repositories

import com.example.whoishakaton.data.local.db.entities.DomainEntity

interface DomainRepository {

    suspend fun addDomainToFavorites(domain: DomainEntity): Long

    suspend fun removeDomainFromFavorites(domain: DomainEntity)

    suspend fun getAllFavoriteDomains(): List<DomainEntity>
}
