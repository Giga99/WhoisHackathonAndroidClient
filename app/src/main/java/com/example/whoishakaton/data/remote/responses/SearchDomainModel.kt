package com.example.whoishakaton.data.remote.responses

import com.squareup.moshi.JsonClass

data class SearchDomainRequest(
    val title: String
)

@JsonClass(generateAdapter = true)
data class SearchDomainResponse(
    val id: String,
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
