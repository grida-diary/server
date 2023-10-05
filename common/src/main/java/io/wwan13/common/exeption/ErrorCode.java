package io.wwan13.common.exeption;

public interface ErrorCode {
    int getHttpStatus();
    String getMessage();
    String name();
}
