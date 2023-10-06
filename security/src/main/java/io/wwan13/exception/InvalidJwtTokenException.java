package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

public class InvalidJwtTokenException extends BaseException {
    public InvalidJwtTokenException() {
        super(SecurityErrorCode.INVALID_JWT_TOKEN);
    }
}
