package az.algoritma.recruitmenttest.domain.usecase

import az.algoritma.recruitmenttest.domain.model.ConnectionState
import az.algoritma.recruitmenttest.domain.repository.MarketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveConnectionStateUseCase @Inject constructor(
    private val repository: MarketRepository
) {
    operator fun invoke(): Flow<ConnectionState> = repository.observeConnectionState()
}