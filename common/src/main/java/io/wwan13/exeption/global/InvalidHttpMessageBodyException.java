package io.wwan13.exeption.global;

import io.wwan13.exeption.base.BaseException;

public class InvalidHttpMessageBodyException extends BaseException {
    public InvalidHttpMessageBodyException() {
        super(GlobalErrorCode.INVALID_HTTP_MESSAGE_BODY);
    }
}
