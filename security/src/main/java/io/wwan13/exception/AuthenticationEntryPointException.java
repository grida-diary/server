package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

public class AuthenticationEntryPointException extends BaseException {
    public AuthenticationEntryPointException() {
        super(SecurityErrorCode.AUTHENTICATION_ENTRY_POINT);
    }
}
