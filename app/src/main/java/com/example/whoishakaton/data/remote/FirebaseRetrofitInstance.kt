package com.example.whoishakaton.data.remote

import com.example.whoishakaton.utils.BASE_URL_FIREBASE
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FirebaseRetrofitInstance {

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL_FIREBASE)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        val api by lazy {
            retrofit.create(FirebaseRetrofit::class.java)
        }
    }
}