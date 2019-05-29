package com.example

import com.theoxao.common.CommonResult
import com.theoxao.common.ParamWrap

import java.util.concurrent.CompletableFuture


class Foo {
    static CompletableFuture<CommonResult> service(ParamWrap paramWrap) {
        List<String> result = new ArrayList<String>()
        def localUserFuture = paramWrap.servicesHolder.httpClient.getFuture("http://git.theoxao.com")
        return localUserFuture.thenApplyAsync({ localUser ->
            return new CommonResult(localUser.substring(0, 10))
        })
    }
}

def bean = new Foo()

