package io.wwan13.common.exeption.global;

import io.wwan13.common.exeption.base.BaseException;
import io.wwan13.common.exeption.base.ErrorCode;

public class BadRequestErrorException extends BaseException {
    public BadRequestErrorException() {
        super(GlobalErrorCode.BAD_REQUEST_ERROR);
    }
}
