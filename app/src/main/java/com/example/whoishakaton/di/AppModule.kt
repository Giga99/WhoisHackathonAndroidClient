package com.example.whoishakaton.di

import android.content.Context
import androidx.room.Room
import com.example.whoishakaton.data.local.db.DomainDAO
import com.example.whoishakaton.data.local.db.DomainHistoryDAO
import com.example.whoishakaton.data.local.db.WhoisDatabase
import com.example.whoishakaton.data.remote.WhoisRetrofit
import com.example.whoishakaton.data.remote.initChuckerInterceptor
import com.example.whoishakaton.utils.BASE_URL
import com.example.whoishakaton.utils.WHOIS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesWhoisDatabase(
        @ApplicationContext context: Context
    ): WhoisDatabase = Room.databaseBuilder(
        context,
        WhoisDatabase::class.java,
        WHOIS_DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providesDomainDAO(whoisDatabase: WhoisDatabase): DomainDAO = whoisDatabase.getDomainDAO()

    @Singleton
    @Provides
    fun providesDomainHistoryDAO(whoisDatabase: WhoisDatabase): DomainHistoryDAO =
        whoisDatabase.getDomainHistoryDAO()

    @Singleton
    @Provides
    fun providesOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(initChuckerInterceptor(context)).build()

    // TODO add base URL
    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun providesWhoisRetrofit(retrofit: Retrofit): WhoisRetrofit =
        retrofit.create(WhoisRetrofit::class.java)
}
