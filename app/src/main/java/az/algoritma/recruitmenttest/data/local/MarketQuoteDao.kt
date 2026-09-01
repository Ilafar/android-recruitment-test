package az.algoritma.recruitmenttest.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MarketQuoteDao {
    @Query("SELECT * FROM market_quote ORDER BY symbol ASC")
    fun observeAll(): Flow<List<MarketQuoteEntity>>

    @Upsert
    suspend fun upsertAll(quotes: List<MarketQuoteEntity>)
}