package com.example


import com.theoxao.common.ParamWrap

import java.util.concurrent.CompletableFuture

static CompletableFuture<?> service(ParamWrap paramWrap) {
    CompletableFuture<String> future = paramWrap.servicesHolder.httpClient.getFuture("http://git.theoxao.com")
    null
}

class UserView {
    String id
    String name
    int age

    static UserView from(User user) {
        def view = new UserView()
        view.id = user.id.toHexString()
        view.name = user.name.toString()
        view.age = user.age
        view
    }
}