package com.example.whoishakaton.data.network.repositories

import com.example.whoishakaton.data.network.WhoisRetrofit
import javax.inject.Inject

class WhoisRepository @Inject constructor(
    val whoisRetrofit: WhoisRetrofit
) {
}
