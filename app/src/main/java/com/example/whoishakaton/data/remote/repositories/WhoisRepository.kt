package com.example.whoishakaton.data.remote.repositories

import com.example.whoishakaton.data.remote.WhoisRetrofit
import javax.inject.Inject

class WhoisRepository @Inject constructor(
    val whoisRetrofit: WhoisRetrofit
) {
}
