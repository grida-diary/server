package io.wwan13.security.exception;

import io.wwan13.common.exeption.base.BaseException;

public class ExpiredJwtTokenException extends BaseException {
    public ExpiredJwtTokenException() {
        super(SecurityErrorCode.EXPIRED_JWT_TOKEN);
    }
}
