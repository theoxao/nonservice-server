package com.theoxao.future

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.future
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture


/**
 * @author theo
 * @date 2019/5/24
 */
@Component
class DelayFuture {

    fun futureDelay(time: Long): CompletableFuture<Unit> {
        return GlobalScope.future {
            delay(time)
        }
    }

}