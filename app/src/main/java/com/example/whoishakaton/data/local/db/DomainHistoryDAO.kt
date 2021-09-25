package com.example.whoishakaton.data.local.db

import androidx.room.*
import com.example.whoishakaton.data.local.db.entities.DomainHistoryEntity

@Dao
interface DomainHistoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(domain: DomainHistoryEntity): Long

    @Delete()
    suspend fun delete(domain: DomainHistoryEntity)

    @Query("SELECT * FROM DOMAIN_HISTORY_TABLE")
    suspend fun getAllSearchedDomains(): List<DomainHistoryEntity>
}
