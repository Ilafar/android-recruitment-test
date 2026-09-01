package az.algoritma.recruitmenttest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MarketQuoteDto(
    @SerializedName("0") val direction: String,
    @SerializedName("1") val symbol: String,
    @SerializedName("2") val bid: String,
    @SerializedName("3") val ask: String,
    @SerializedName("4") val low: String,
    @SerializedName("5") val high: String,
    @SerializedName("6") val spreadPoints: Int,
    @SerializedName("7") val updatedAt: String
)
