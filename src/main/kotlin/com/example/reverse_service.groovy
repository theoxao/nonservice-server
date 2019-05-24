package com.example

import com.theoxao.common.ParamWrap
import java.util.concurrent.CompletableFuture

static CompletableFuture<String> service(ParamWrap paramWrap) {
    return paramWrap.servicesHolder.delayFuture.futureDelay(4000).thenApplyAsync({ f ->
        return paramWrap.call.request.queryParameters.get("name").reverse()
    })
}
