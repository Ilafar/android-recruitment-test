package az.algoritma.recruitmenttest.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.algoritma.recruitmenttest.domain.model.MarketQuote
import az.algoritma.recruitmenttest.domain.model.TrendDirection
import az.algoritma.recruitmenttest.domain.usecase.ConnectToMarketUseCase
import az.algoritma.recruitmenttest.domain.usecase.DisconnectFromMarketUseCase
import az.algoritma.recruitmenttest.domain.usecase.ObserveConnectionStateUseCase
import az.algoritma.recruitmenttest.domain.usecase.ObserveMarketQuotesUseCase
import az.algoritma.recruitmenttest.ui.component.UiMarketQuote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val observeQuotes: ObserveMarketQuotesUseCase,
    private val observeConnectionState: ObserveConnectionStateUseCase,
    private val connectToMarket: ConnectToMarketUseCase,
    private val disconnectFromMarket: DisconnectFromMarketUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<MarketUiState> = MutableStateFlow(MarketUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(observeQuotes(), observeConnectionState()) { quotes, connectionState ->
                MarketUiState(
                    quotes.map { it.toUiModel() },
                    connectionState
                )
            }.collect { _uiState.value = it }
        }
        connectToMarket()
    }

    override fun onCleared() {
        disconnectFromMarket()
        super.onCleared()
    }
}

private fun MarketQuote.toUiModel(): UiMarketQuote {
    val changePercent = when (direction) {
        TrendDirection.UP -> if (low > 0) ((bid - low) / low) * 100 else 0.0
        TrendDirection.DOWN -> if (high > 0) ((high - bid) / high) * 100 else 0.0
    }
    val isPositive = direction == TrendDirection.UP
    val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    return UiMarketQuote(
        symbol = symbol,
        price = "%.5f".format(bid),
        dayRange = "L %.5f · H %.5f".format(low, high),
        metaText = "Spread: $spreadPoints pts · Ask %.5f · %s".format(
            ask,
            timeFormat.format(updatedAtEpochMillis)
        ),
        changeText = "${if (isPositive) "↑" else "↓"}${"%.2f".format(changePercent)}%",
        isPositive = isPositive
    )
}
