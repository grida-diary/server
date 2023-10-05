package io.wwan13.common.exeption.base;

public interface ErrorCode {
    int getHttpStatus();
    String getMessage();
    String name();
}
