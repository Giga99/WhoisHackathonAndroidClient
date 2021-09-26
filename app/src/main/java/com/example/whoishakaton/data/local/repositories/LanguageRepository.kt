package com.example.whoishakaton.data.local.repositories

import com.example.whoishakaton.data.local.db.LanguageDAO
import com.example.whoishakaton.data.local.db.entities.LanguageEntity
import javax.inject.Inject

class LanguageRepository @Inject constructor(
    private val languageDAO: LanguageDAO
) {

    suspend fun addLanguage(lang: LanguageEntity): Long = languageDAO.upsert(lang)

    suspend fun removeLanguage(lang: LanguageEntity) = languageDAO.delete(lang)

    suspend fun getLanguageById(id: Long) = languageDAO.getLanguageById(id)
}
