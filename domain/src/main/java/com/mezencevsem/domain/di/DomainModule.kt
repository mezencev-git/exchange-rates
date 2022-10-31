package com.mezencevsem.domain.di

import com.mezencevsem.data.ExchangeRatesRepository
import com.mezencevsem.domain.ExchangeRatesInteractor
import com.mezencevsem.domain.ExchangeRatesInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    internal fun provideExchangeRatesInteractor(
        repository: ExchangeRatesRepository
    ): ExchangeRatesInteractor = ExchangeRatesInteractorImpl(
        repository = repository
    )
}