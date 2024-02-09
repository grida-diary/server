package org.grida.exception;

public class DomainRdsException extends BaseException {

    public DomainRdsException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
