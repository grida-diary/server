package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

public class WrongJwtTokenException extends BaseException {
    public WrongJwtTokenException() {
        super(SecurityErrorCode.WRONG_JWT_TOKEN);
    }
}
