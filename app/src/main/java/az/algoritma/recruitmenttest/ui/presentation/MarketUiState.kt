package az.algoritma.recruitmenttest.ui.presentation

import az.algoritma.recruitmenttest.domain.model.ConnectionState
import az.algoritma.recruitmenttest.ui.component.UiMarketQuote

data class MarketUiState(
    val quotes: List<UiMarketQuote> = emptyList(),
    val connectionState: ConnectionState = ConnectionState.Connecting
)
