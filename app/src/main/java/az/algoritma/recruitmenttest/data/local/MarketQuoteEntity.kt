package az.algoritma.recruitmenttest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "market_quote")
data class MarketQuoteEntity(
    @PrimaryKey val symbol: String,
    val direction: String,
    val bid: Double,
    val ask: Double,
    val low: Double,
    val high: Double,
    val spreadPoints: Int,
    val updatedAtEpochMillis: Long
)
