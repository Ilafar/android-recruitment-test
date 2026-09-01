package az.algoritma.recruitmenttest.data.mapper

import az.algoritma.recruitmenttest.data.local.MarketQuoteEntity
import az.algoritma.recruitmenttest.data.remote.dto.MarketQuoteDto
import az.algoritma.recruitmenttest.domain.model.MarketQuote
import az.algoritma.recruitmenttest.domain.model.TrendDirection
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun MarketQuoteDto.toEntity(): MarketQuoteEntity {
    val isoFormat = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US
    ).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    return MarketQuoteEntity(
        symbol = symbol,
        direction = direction,
        bid = bid.toDoubleOrNull() ?: 0.0,
        ask = ask.toDoubleOrNull() ?: 0.0,
        low = low.toDoubleOrNull() ?: 0.0,
        high = high.toDoubleOrNull() ?: 0.0,
        spreadPoints = spreadPoints,
        updatedAtEpochMillis = runCatching { isoFormat.parse(updatedAt)?.time }
            .getOrNull() ?: System.currentTimeMillis()
    )
}

fun MarketQuoteEntity.toDomain(): MarketQuote = MarketQuote(
    symbol = symbol,
    direction = if (direction == "up") TrendDirection.UP else TrendDirection.DOWN,
    bid = bid,
    ask = ask,
    low = low,
    high = high,
    spreadPoints = spreadPoints,
    updatedAtEpochMillis = updatedAtEpochMillis
)