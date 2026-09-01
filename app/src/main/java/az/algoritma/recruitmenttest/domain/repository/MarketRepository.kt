package az.algoritma.recruitmenttest.domain.repository

import az.algoritma.recruitmenttest.domain.model.ConnectionState
import az.algoritma.recruitmenttest.domain.model.MarketQuote
import kotlinx.coroutines.flow.Flow

interface MarketRepository {
    fun observeQuotes(): Flow<List<MarketQuote>>
    fun observeConnectionState(): Flow<ConnectionState>
    fun connect()
    fun disconnect()
}