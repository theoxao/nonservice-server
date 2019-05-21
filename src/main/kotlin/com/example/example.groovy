package com.example

import com.theoxao.common.CommonResult
import com.theoxao.common.ParamWrap
import java.util.concurrent.CompletableFuture
import java.util.function.Function

static CompletableFuture<CommonResult> service(ParamWrap paramWrap) {
    List<String> result = new ArrayList<String>()
    def localUserFuture = paramWrap.servicesHolder.httpClient.getFuture("http://git.theoxao.com")
    localUserFuture.
    localUserFuture.thenAcceptBoth()

    localUserFuture.thenApplyAsync(new Function<String, CommonResult>() {
        @Override
        CommonResult apply(String localUser) {
            result.add(localUser)
            def userFuture = paramWrap.servicesHolder.httpClient.getFuture("http://git.theoxao.com")
            userFuture.thenApplyAsync(new Function<String, CommonResult>() {
                @Override
                CommonResult apply(String user) {
                    result.add(user)
                    return null
                }
            })
            return new CommonResult(result)
        }
    })

}