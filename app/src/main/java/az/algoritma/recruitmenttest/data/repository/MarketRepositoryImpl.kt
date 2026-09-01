package az.algoritma.recruitmenttest.data.repository

import az.algoritma.recruitmenttest.data.local.MarketQuoteDao
import az.algoritma.recruitmenttest.data.mapper.toDomain
import az.algoritma.recruitmenttest.data.mapper.toEntity
import az.algoritma.recruitmenttest.data.remote.MarketSocketDataSource
import az.algoritma.recruitmenttest.domain.model.ConnectionState
import az.algoritma.recruitmenttest.domain.model.MarketQuote
import az.algoritma.recruitmenttest.domain.repository.MarketRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MarketRepositoryImpl @Inject constructor(
    private val socketDataSource: MarketSocketDataSource,
    private val dao: MarketQuoteDao,
    private val externalScope: CoroutineScope
) : MarketRepository {
    override fun observeQuotes(): Flow<List<MarketQuote>> {
        return dao.observeAll().map { entities ->
            entities.map { entity ->
                entity.toDomain()
            }
        }
    }

    override fun observeConnectionState(): Flow<ConnectionState> {
        return socketDataSource.observeConnectionState()
    }

    override fun connect() {
        socketDataSource.observeMarketQuotes()
            .onEach { response ->
                val entities = response.result.map {
                    it.toEntity()
                }
                dao.upsertAll(entities)
            }
            .launchIn(externalScope)

        socketDataSource.connect()
    }

    override fun disconnect() {
        socketDataSource.disconnect()
    }
}