package com.theoxao.http

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.future.future
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture


/**
 * @author theo
 * @date 2019/5/21
 */
@Component
@KtorExperimentalAPI
class HttpClient {

    private val client = HttpClient(CIO) {
        engine {
            maxConnectionsCount = 1000
        }
    }

    suspend fun get(url: String): String {
        return client.get(url)
    }

    fun getFuture(url: String): CompletableFuture<String> {
        return GlobalScope.future {
            get(url)
        }
    }
}