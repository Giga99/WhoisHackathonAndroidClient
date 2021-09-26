package com.example.whoishakaton.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.whoishakaton.data.local.db.entities.DomainEntity
import com.example.whoishakaton.data.local.db.entities.DomainHistoryEntity
import com.example.whoishakaton.data.local.db.entities.LanguageEntity

@Database(
    entities = [
        DomainEntity::class,
        DomainHistoryEntity::class,
        LanguageEntity::class
    ],
    version = 5
)
abstract class WhoisDatabase : RoomDatabase() {

    abstract fun getDomainDAO(): DomainDAO

    abstract fun getDomainHistoryDAO(): DomainHistoryDAO

    abstract fun getLanguageDAO(): LanguageDAO
}
