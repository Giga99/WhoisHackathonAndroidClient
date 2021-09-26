package com.example.whoishakaton.data.local.db

import androidx.room.*
import com.example.whoishakaton.data.local.db.entities.LanguageEntity

@Dao
interface LanguageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(lang: LanguageEntity): Long

    @Delete
    suspend fun delete(lang: LanguageEntity)

    @Query("SELECT * FROM LANGUAGE_TABLE WHERE id = :id")
    suspend fun getLanguageById(id: Long): LanguageEntity?
}
