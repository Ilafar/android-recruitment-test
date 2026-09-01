package az.algoritma.recruitmenttest.domain.usecase

import az.algoritma.recruitmenttest.domain.model.MarketQuote
import az.algoritma.recruitmenttest.domain.repository.MarketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMarketQuotesUseCase @Inject constructor(
    private val repository: MarketRepository
) {
    operator fun invoke(): Flow<List<MarketQuote>> = repository.observeQuotes()
}