package org.grida.validator.exception;

import org.grida.exception.BaseException;
import org.grida.exception.ErrorCode;

public class RequestFieldException extends BaseException {

    public RequestFieldException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
