package com.example.whoishakaton.domain.use_cases.local

import com.example.whoishakaton.data.local.db.entities.LanguageEntity
import com.example.whoishakaton.data.local.repositories.LanguageRepository
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class AddLanguageUseCase @Inject constructor(
    private val languageRepository: LanguageRepository
) {

    suspend fun execute(lang: LanguageEntity): Resource<Unit> =
        try {
            languageRepository.addLanguage(lang)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
}
