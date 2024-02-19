package org.grida.exception;

public class DomainImageException extends BaseException {

    public DomainImageException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
