package io.wwan13.exeption.base;

public interface ErrorCode {
    int getHttpStatus();
    String getMessage();
    String name();
}
