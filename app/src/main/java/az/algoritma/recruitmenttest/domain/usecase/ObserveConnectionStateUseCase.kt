package az.algoritma.recruitmenttest.domain.usecase

import az.algoritma.recruitmenttest.domain.repository.MarketRepository

class ObserveConnectionStateUseCase(private val repository: MarketRepository) {
    operator fun invoke() = repository.observeConnectionState()
}