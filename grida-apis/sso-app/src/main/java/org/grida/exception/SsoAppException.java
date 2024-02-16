package org.grida.exception;

public class SsoAppException extends BaseException {

    public SsoAppException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
