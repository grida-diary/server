package io.wwan13.security.exception;

import io.wwan13.common.exeption.BaseException;
import io.wwan13.common.exeption.ErrorCode;

public class UnsupportedJwtTokenException extends BaseException {
    public UnsupportedJwtTokenException() {
        super(SecurityErrorCode.UNSUPPORTED_JWT_TOKEN);
    }
}
