package az.algoritma.recruitmenttest.domain.model

enum class TrendDirection { UP, DOWN }

data class MarketQuote(
    val symbol: String,
    val direction: TrendDirection,
    val bid: Double,
    val ask: Double,
    val low: Double,
    val high: Double,
    val spreadPoints: Int,
    val updatedAtEpochMillis: Long
)
