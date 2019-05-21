package com.theoxao.wrap;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.jetbrains.annotations.NotNull;

/**
 * @author theo
 * @date 2019/5/21
 */
public abstract class ContinuationWrap<T> implements Continuation<T> {
    @NotNull
    @Override
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override
    public void resumeWith(@NotNull Object any) {
        onResume(any);
    }

    public void onResume(Object any) {
    }
}
