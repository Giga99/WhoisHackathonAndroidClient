package com.example.whoishakaton.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.whoishakaton.data.local.db.entities.DomainEntity
import com.example.whoishakaton.data.local.db.entities.DomainHistoryEntity

@Database(
    entities = [
        DomainEntity::class,
        DomainHistoryEntity::class
    ],
    version = 4
)
abstract class WhoisDatabase : RoomDatabase() {

    abstract fun getDomainDAO(): DomainDAO

    abstract fun getDomainHistoryDAO(): DomainHistoryDAO
}
