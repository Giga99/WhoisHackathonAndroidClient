package com.example.whoishakaton.data.remote.repositories

import com.example.whoishakaton.data.remote.WhoisRetrofit
import com.example.whoishakaton.domain.repositories.WhoisRepository
import javax.inject.Inject

class WhoisRepositoryImpl @Inject constructor(
    val whoisRetrofit: WhoisRetrofit
): WhoisRepository {
}
