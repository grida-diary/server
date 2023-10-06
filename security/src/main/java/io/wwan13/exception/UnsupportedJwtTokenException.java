package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

public class UnsupportedJwtTokenException extends BaseException {
    public UnsupportedJwtTokenException() {
        super(SecurityErrorCode.UNSUPPORTED_JWT_TOKEN);
    }
}
