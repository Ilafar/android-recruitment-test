package az.algoritma.recruitmenttest.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val SOCKET_URL = "https://q.investaz.az"
    private const val SOCKET_PATH = "/live/"

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideSocket(): Socket {
        val options = IO.Options().apply {
            path = SOCKET_PATH
            reconnection = true
            forceNew = true
        }
        return try {
            IO.socket(SOCKET_URL, options)
        } catch (e: URISyntaxException) {
            throw IllegalStateException("Invalid socket URL: $SOCKET_URL", e)
        }
    }
}