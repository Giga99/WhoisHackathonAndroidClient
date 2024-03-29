package com.example.whoishakaton.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.whoishakaton.utils.DOMAIN_HISTORY_TABLE_NAME

@Entity(tableName = DOMAIN_HISTORY_TABLE_NAME)
data class DomainHistoryEntity(
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val date: Long
)
