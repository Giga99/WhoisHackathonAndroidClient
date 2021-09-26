package com.example.whoishakaton.domain.use_cases.local

import com.example.whoishakaton.data.local.db.entities.LanguageEntity
import com.example.whoishakaton.data.local.repositories.LanguageRepository
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class GetLanguageByIdUseCase @Inject constructor(
    private val languageRepository: LanguageRepository
) {

    suspend fun execute(id: Long): Resource<LanguageEntity?> =
        try {
            Resource.Success(languageRepository.getLanguageById(id))
        } catch (e: Exception) {
            Resource.Failure(e)
        }
}
