package com.example.whoishakaton.data.local.db

import androidx.room.*
import com.example.whoishakaton.data.local.db.entities.DomainEntity

@Dao
interface DomainDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(domain: DomainEntity): Long

    @Delete()
    suspend fun delete(domain: DomainEntity)

    @Query("SELECT * FROM DOMAIN_TABLE")
    suspend fun getAllDomains(): List<DomainEntity>

    @Query("SELECT * FROM DOMAIN_TABLE WHERE id = :id")
    suspend fun getDomainById(id: String): DomainEntity
}
