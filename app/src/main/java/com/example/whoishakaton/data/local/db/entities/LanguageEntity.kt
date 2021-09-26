package com.example.whoishakaton.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.whoishakaton.utils.LANGUAGE_TABLE_NAME

@Entity(tableName = LANGUAGE_TABLE_NAME)
data class LanguageEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 1,
    val language: String
)
