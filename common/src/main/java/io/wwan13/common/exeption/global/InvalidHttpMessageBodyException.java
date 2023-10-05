package io.wwan13.common.exeption.global;

import io.wwan13.common.exeption.base.BaseException;
import io.wwan13.common.exeption.base.ErrorCode;

public class InvalidHttpMessageBodyException extends BaseException {
    public InvalidHttpMessageBodyException() {
        super(GlobalErrorCode.INVALID_HTTP_MESSAGE_BODY);
    }
}
