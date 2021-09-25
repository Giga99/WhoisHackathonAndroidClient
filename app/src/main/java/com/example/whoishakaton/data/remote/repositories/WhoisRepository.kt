package com.example.whoishakaton.data.remote.repositories

import com.example.whoishakaton.data.remote.WhoisRetrofit
import com.example.whoishakaton.data.remote.responses.SearchDomainRequest
import javax.inject.Inject

class WhoisRepository @Inject constructor(
    private val whoisRetrofit: WhoisRetrofit
) {

    suspend fun searchDomain(domainRequest: SearchDomainRequest) =
        whoisRetrofit.searchDomain(domainRequest.title)
}
