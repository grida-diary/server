package io.wwan13.security.exception;

import io.wwan13.common.exeption.BaseException;
import io.wwan13.common.exeption.ErrorCode;

public class AuthenticationEntryPointException extends BaseException {
    public AuthenticationEntryPointException() {
        super(SecurityErrorCode.AUTHENTICATION_ENTRY_POINT);
    }
}
