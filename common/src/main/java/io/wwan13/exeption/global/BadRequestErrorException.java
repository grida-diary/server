package io.wwan13.exeption.global;

import io.wwan13.exeption.base.BaseException;

public class BadRequestErrorException extends BaseException {
    public BadRequestErrorException() {
        super(GlobalErrorCode.BAD_REQUEST_ERROR);
    }
}
