package com.example.whoishakaton.data.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor

fun initChuckerInterceptor(context: Context) =
    ChuckerInterceptor.Builder(context).build() as Interceptor
