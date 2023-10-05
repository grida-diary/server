package io.wwan13.security.exception;

import io.wwan13.common.exeption.BaseException;
import io.wwan13.common.exeption.ErrorCode;

public class WrongJwtTokenException extends BaseException {
    public WrongJwtTokenException() {
        super(SecurityErrorCode.WRONG_JWT_TOKEN);
    }
}
