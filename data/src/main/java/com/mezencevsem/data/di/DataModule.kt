package com.mezencevsem.data.di

import android.content.Context
import androidx.room.Room
import com.mezencevsem.data.ExchangeRatesRepository
import com.mezencevsem.data.ExchangeRatesRepositoryImpl
import com.mezencevsem.data.local.CurrenciesDatabase
import com.mezencevsem.data.remote.ExchangeRatesApi
import com.mezencevsem.data.remote.ExchangeRatesApiImpl
import com.mezencevsem.data.remote.ktorHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    internal fun provideCurrenciesDatabase(
        @ApplicationContext appContext: Context
    ): CurrenciesDatabase =
        Room.databaseBuilder(
            appContext,
            CurrenciesDatabase::class.java,
            CurrenciesDatabase.NAME
        ).build()

    @Singleton
    @Provides
    internal fun provideExchangeRatesApi(): ExchangeRatesApi = ExchangeRatesApiImpl(
        client = ktorHttpClient
    )

    @Singleton
    @Provides
    internal fun provideExchangeRatesRepository(
        database: CurrenciesDatabase,
        api: ExchangeRatesApi
    ): ExchangeRatesRepository = ExchangeRatesRepositoryImpl(
        database = database,
        api = api
    )
}