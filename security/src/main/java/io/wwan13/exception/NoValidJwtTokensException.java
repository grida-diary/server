package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

public class NoValidJwtTokensException extends BaseException {
    public NoValidJwtTokensException() {
        super(SecurityErrorCode.NO_VALID_JWT_TOKENS);
    }
}
