package com.example.whoishakaton.domain.models

import com.example.whoishakaton.data.local.db.entities.DomainHistoryEntity

data class DomainHistoryUIModel(
    val title: String,
    val date: Long
)

fun DomainHistoryEntity.fromEntityToUIModel() = DomainHistoryUIModel(title, date)

fun DomainHistoryUIModel.fromUIModelToEntity() = DomainHistoryEntity(title, date)
