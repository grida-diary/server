package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

public class ExpiredJwtTokenException extends BaseException {
    public ExpiredJwtTokenException() {
        super(SecurityErrorCode.EXPIRED_JWT_TOKEN);
    }
}
