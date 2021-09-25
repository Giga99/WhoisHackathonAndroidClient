package com.example.whoishakaton.data.local.repositories

import com.example.whoishakaton.data.local.db.DomainDAO
import javax.inject.Inject

class DomainRepository @Inject constructor(
    val domainDAO: DomainDAO
) {
}
