package com.example.whoishakaton.data.remote.repositories

import com.example.whoishakaton.data.remote.WhoisRetrofit
import com.example.whoishakaton.data.remote.responses.SearchDomainRequest
import com.example.whoishakaton.utils.WHOIS_SERVER
import javax.inject.Inject
import javax.inject.Named

class WhoisRepository @Inject constructor(
    @Named(WHOIS_SERVER) private val whoisRetrofit: WhoisRetrofit
) {

    suspend fun searchDomain(domainRequest: SearchDomainRequest) =
        whoisRetrofit.searchDomain(domainRequest.title)

    suspend fun getPopularDomains() = whoisRetrofit.getPopularDomains()

    suspend fun getRandomDomain() = whoisRetrofit.getRandomDomain()
}
