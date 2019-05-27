package com.example

import com.theoxao.common.ParamWrap

import java.util.concurrent.CompletableFuture

static CompletableFuture<String> service(ParamWrap paramWrap) {
    String name = paramWrap.call.request.queryParameters.get("name")
    return paramWrap.servicesHolder.httpClient.getFuture("http://127.0.0.1:8088/fake_tp?name=" + name)
}
