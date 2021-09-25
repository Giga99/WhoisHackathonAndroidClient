package com.example.whoishakaton.data.remote

import com.example.whoishakaton.data.remote.responses.SearchDomainResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WhoisRetrofit {

    @GET("WhoIs/")
    suspend fun searchDomain(
        @Query("domain") domain: String
    ): SearchDomainResponse
}
