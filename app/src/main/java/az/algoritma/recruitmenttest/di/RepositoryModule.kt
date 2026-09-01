package az.algoritma.recruitmenttest.di

import az.algoritma.recruitmenttest.data.repository.MarketRepositoryImpl
import az.algoritma.recruitmenttest.domain.repository.MarketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMarketRepository(impl: MarketRepositoryImpl): MarketRepository
}