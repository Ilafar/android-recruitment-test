package az.algoritma.recruitmenttest.data.remote

import android.util.Log
import az.algoritma.recruitmenttest.data.remote.dto.MarketSnapshotResponse
import az.algoritma.recruitmenttest.domain.model.ConnectionState
import com.google.gson.Gson
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class MarketSocketDataSource @Inject constructor(
    private val socket: Socket,
    private val gson: Gson
) {
    fun connect() {
        if (!socket.connected()) socket.connect()
    }

    fun disconnect() {
        socket.disconnect()
    }

    fun observeConnectionState(): Flow<ConnectionState> = callbackFlow {
        val onConnect = Emitter.Listener { trySend(ConnectionState.Connected) }
        val onReconnecting = Emitter.Listener { trySend(ConnectionState.Connecting) }
        val onDisconnect = Emitter.Listener { args ->
            val reason = args.firstOrNull()?.toString()
            trySend(ConnectionState.Disconnected(reason))
            Log.e(TAG, reason.toString())
        }
        val onConnectError = Emitter.Listener { args ->
            val error = args.firstOrNull()
            val reason = error?.toString() ?: "Unknown error"
            trySend(ConnectionState.Disconnected(reason))
            Log.e(TAG, "connect_error: $reason", error as? Throwable)
        }

        socket.on(Socket.EVENT_CONNECT, onConnect)
        socket.on(Socket.EVENT_DISCONNECT, onDisconnect)
        socket.on(Socket.EVENT_RECONNECTING, onReconnecting)
        socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError)

        val initialConnectionState =
            if (socket.connected()) ConnectionState.Connected else ConnectionState.Connecting
        trySend(initialConnectionState)

        awaitClose {
            socket.off(Socket.EVENT_CONNECT, onConnect)
            socket.off(Socket.EVENT_DISCONNECT, onDisconnect)
            socket.off(Socket.EVENT_RECONNECTING, onReconnecting)
            socket.off(Socket.EVENT_CONNECT_ERROR, onConnectError)
        }
    }

    fun observeMarketQuotes(eventName: String = MARKET_EVENT): Flow<MarketSnapshotResponse> =
        callbackFlow {
            val listener = Emitter.Listener { args ->
                val data = args.firstOrNull()?.toString()

                data?.let { raw ->
                    runCatching {
                        gson.fromJson(raw, MarketSnapshotResponse::class.java)
                    }
                        .onSuccess { trySend(it) }
                        .onFailure { Log.e(TAG, "Failed to parse market payload", it) }
                }
            }
            socket.on(eventName, listener)
            awaitClose { socket.off(eventName, listener) }
        }

    companion object {
        private const val TAG = "MarketSocketDataSource"
        const val MARKET_EVENT = "message"
    }
}