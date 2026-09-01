package az.algoritma.recruitmenttest.data.remote.dto

data class MarketSnapshotResponse(
    val result: List<MarketQuoteDto>,
    val total: Int
)