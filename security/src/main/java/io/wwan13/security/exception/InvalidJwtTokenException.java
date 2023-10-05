package io.wwan13.security.exception;

import io.wwan13.common.exeption.base.BaseException;

public class InvalidJwtTokenException extends BaseException {
    public InvalidJwtTokenException() {
        super(SecurityErrorCode.INVALID_JWT_TOKEN);
    }
}
