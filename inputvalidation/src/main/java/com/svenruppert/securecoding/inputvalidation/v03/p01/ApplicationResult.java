package com.svenruppert.securecoding.inputvalidation.v03.p01;

import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public record ApplicationResult<T>(boolean success, T data, String message) {
    public void ifSuccess(Consumer<T> consumer) {
        if (success) {
            consumer.accept(data);
        }
    }
    public void ifError(Consumer<String> consumer) {
        if (!success) {
            consumer.accept(message);
        }
    }
}
