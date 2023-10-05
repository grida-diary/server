package io.wwan13.security.exception;

import io.wwan13.common.exeption.BaseException;
import io.wwan13.common.exeption.ErrorCode;

public class NoValidJwtTokensException extends BaseException {
    public NoValidJwtTokensException() {
        super(SecurityErrorCode.NO_VALID_JWT_TOKENS);
    }
}
