package az.algoritma.recruitmenttest.domain.usecase

import az.algoritma.recruitmenttest.domain.repository.MarketRepository

class DisconnectFromMarketUseCase @Inject constructor(
    private val repository: MarketRepository
) {
    operator fun invoke() = repository.disconnect()
}