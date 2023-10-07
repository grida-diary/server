package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class NoSuchAuthenticationException extends BaseException {
    public NoSuchAuthenticationException() {
        super(SecurityErrorCode.NO_SUCH_AUTHENTICATION);
    }
}
