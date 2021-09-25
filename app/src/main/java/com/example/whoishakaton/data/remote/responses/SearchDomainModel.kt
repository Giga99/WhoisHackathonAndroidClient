package com.example.whoishakaton.data.remote.responses

import com.squareup.moshi.JsonClass

data class SearchDomainRequest(
    val title: String
)

@JsonClass(generateAdapter = true)
data class SearchDomainResponse(
    val information: DomainInformation
)

@JsonClass(generateAdapter = true)
data class DomainInformation(
    val id: String,
    val domainId: String?,
    val name: String,
    val createdDate: String?,
    val updatedDate: String?,
    val expirationDate: String?,
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
