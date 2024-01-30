package org.grida.exception;

public class DomainAiException extends BaseException {
    public DomainAiException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
