package org.grida.exception;

public class EndUserAppException extends BaseException {
    public EndUserAppException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
