package com.example.whoishakaton.domain.models

import com.example.whoishakaton.data.local.db.entities.DomainEntity
import com.example.whoishakaton.data.remote.responses.SearchDomainResponse

data class DomainInformationUIModel(
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

fun SearchDomainResponse.fromResponseToUIModel() = DomainInformationUIModel(
    information.id,
    information.domainId,
    information.name,
    information.createdDate,
    information.updatedDate,
    information.expirationDate,
    information.nameServers,
    information.address,
    information.postalCode,
    information.registarIanaId,
    information.registarName,
    information.url,
    information.abuseContactEmail,
    information.abuseContactPhone,
    information.registrantName,
    information.whoIsResponse,
)

fun DomainEntity.fromEntityToUIModel() = DomainInformationUIModel(
    id,
    domainId,
    name,
    createdDate,
    updatedDate,
    expirationDate,
    nameServers,
    address,
    postalCode,
    registarIanaId,
    registarName,
    url,
    abuseContactEmail,
    abuseContactPhone,
    registrantName,
    whoIsResponse
)

fun DomainInformationUIModel.fromUIModelToEntity() = DomainEntity(
    id,
    domainId,
    name,
    createdDate,
    updatedDate,
    expirationDate,
    nameServers,
    address,
    postalCode,
    registarIanaId,
    registarName,
    url,
    abuseContactEmail,
    abuseContactPhone,
    registrantName,
    whoIsResponse
)
