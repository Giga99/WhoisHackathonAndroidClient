package com.example.whoishakaton.domain.use_cases.remote

import com.example.whoishakaton.data.remote.repositories.WhoisRepository
import com.example.whoishakaton.domain.models.DomainInformationUIModel
import com.example.whoishakaton.domain.models.fromResponseToUIModel
import com.example.whoishakaton.utils.Resource
import javax.inject.Inject

class GetRandomDomainUseCase @Inject constructor(
    private val whoisRepository: WhoisRepository
) {

    suspend fun execute(): Resource<DomainInformationUIModel> =
        try {
            val list = whoisRepository.getRandomDomain().fromResponseToUIModel()
            Resource.Success(list)
        } catch (t: Throwable) {
            Resource.Failure(t)
        }
}
