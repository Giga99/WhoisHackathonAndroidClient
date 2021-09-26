package com.example.whoishakaton.domain.models

import com.example.whoishakaton.data.local.db.entities.DomainEntity
import com.example.whoishakaton.data.remote.responses.DomainResponse

data class DomainInformationUIModel(
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

fun DomainInformationUIModel.equal(other: DomainInformationUIModel) =
    id == other.id
            && domainId == other.domainId
            && name == other.name
            && createdDateInMiliseconds == other.createdDateInMiliseconds
            && updatedDateInMiliseconds == other.updatedDateInMiliseconds
            && expirationDateInMiliseconds == other.expirationDateInMiliseconds
            && nameServers == other.nameServers
            && address == other.address
            && postalCode == other.postalCode
            && registarIanaId == other.registarIanaId
            && registarName == other.registarName
            && url == other.url
            && abuseContactEmail == other.abuseContactEmail
            && abuseContactPhone == other.abuseContactPhone
            && registrantName == other.registrantName
            && whoIsResponse == other.whoIsResponse

fun DomainResponse.fromResponseToUIModel() = DomainInformationUIModel(
    id,
    domainId,
    name,
    createdDateInMiliseconds,
    updatedDateInMiliseconds,
    expirationDateInMiliseconds,
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

fun DomainEntity.fromEntityToUIModel() = DomainInformationUIModel(
    id,
    domainId,
    name,
    createdDateInMiliseconds,
    updatedDateInMiliseconds,
    expirationDateInMiliseconds,
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
    createdDateInMiliseconds,
    updatedDateInMiliseconds,
    expirationDateInMiliseconds,
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
