package com.theoxao.wrap;

import com.theoxao.http.HttpClient;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author theo
 * @date 2019/5/21
 */
@Component
public class HttpClientWrap {
    private final HttpClient httpClient;

    public HttpClientWrap(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void get(String url, Consumer<Object> onSuccess) {
        httpClient.get(url, new Continuation<String>() {
            @NotNull
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(@NotNull Object result) {
                onSuccess.accept(result);
            }
        });
    }
}
