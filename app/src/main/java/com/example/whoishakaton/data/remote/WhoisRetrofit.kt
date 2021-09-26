package com.example.whoishakaton.data.remote

import com.example.whoishakaton.data.remote.responses.DomainResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WhoisRetrofit {

    @GET("WhoIs/")
    suspend fun searchDomain(
        @Query("domain") domain: String
    ): DomainResponse

    @GET("WhoIs/popular")
    suspend fun getPopularDomains(): List<DomainResponse>

    @GET("WhoIs/random")
    suspend fun getRandomDomain(): DomainResponse
}
