package az.algoritma.recruitmenttest.domain.model

sealed interface ConnectionState{
    data object Connecting: ConnectionState
    data object Connected: ConnectionState
    data class Disconnected(val reason: String?): ConnectionState
}
