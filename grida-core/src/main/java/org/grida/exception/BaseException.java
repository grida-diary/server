package org.grida.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String errorMessage;

    protected BaseException(ErrorCode errorCode, Object... args) {
        this.errorCode = errorCode;
        this.errorMessage = String.format(errorCode.getMessage(), args);
    }
}
