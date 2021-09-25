package com.example.whoishakaton.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.whoishakaton.utils.DOMAIN_TABLE_NAME

@Entity(tableName = DOMAIN_TABLE_NAME)
data class DomainEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String
)
