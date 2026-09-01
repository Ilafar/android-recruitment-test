package az.algoritma.recruitmenttest.di

import android.content.Context
import androidx.room.Room
import az.algoritma.recruitmenttest.data.local.AppDatabase
import az.algoritma.recruitmenttest.data.local.MarketQuoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "market.db").build()

    @Provides
    fun provideMarketQuoteDao(database: AppDatabase): MarketQuoteDao = database.marketQuoteDao()
}