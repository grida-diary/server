package org.grida.exception;

public class DomainException extends BaseException {
    public DomainException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
