package org.grida.exception;

public class ApisSecurityException extends BaseException {

    public ApisSecurityException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
