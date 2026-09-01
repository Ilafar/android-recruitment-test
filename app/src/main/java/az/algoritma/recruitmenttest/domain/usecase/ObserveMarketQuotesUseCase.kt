package az.algoritma.recruitmenttest.domain.usecase

import az.algoritma.recruitmenttest.domain.repository.MarketRepository

class ObserveMarketQuotesUseCase(private val repository: MarketRepository) {
    operator fun invoke() = repository.observeQuotes()
}