package com.example.whoishakaton.data.remote.responses

data class SearchDomainRequest(
    val title: String
)

data class SearchDomainResponse(
    val domainName: String,
    val domainId: String,
    val whoisServer: String
)
