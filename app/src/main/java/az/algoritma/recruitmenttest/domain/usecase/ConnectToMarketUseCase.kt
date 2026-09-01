package az.algoritma.recruitmenttest.domain.usecase

import az.algoritma.recruitmenttest.domain.repository.MarketRepository
import javax.inject.Inject

class ConnectToMarketUseCase @Inject constructor(
    private val repository: MarketRepository
) {
    operator fun invoke() = repository.connect()
}