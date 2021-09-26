package com.example.whoishakaton.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.whoishakaton.utils.DOMAIN_TABLE_NAME

@Entity(tableName = DOMAIN_TABLE_NAME)
data class DomainEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    val domainId: String?,
    val name: String,
    val createdDateInMiliseconds: Long?,
    val updatedDateInMiliseconds: Long?,
    val expirationDateInMiliseconds: Long?,
    val nameServers: String?,
    val address: String?,
    val postalCode: String?,
    val registarIanaId: String?,
    val registarName: String?,
    val url: String?,
    val abuseContactEmail: String?,
    val abuseContactPhone: String?,
    val registrantName: String?,
    val whoIsResponse: String?
)
