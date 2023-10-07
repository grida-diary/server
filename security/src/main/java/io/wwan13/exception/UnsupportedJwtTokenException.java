package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

import javax.servlet.http.HttpServletResponse;

public class UnsupportedJwtTokenException extends SecurityException {
    public UnsupportedJwtTokenException(HttpServletResponse response) {
        super(SecurityErrorCode.UNSUPPORTED_JWT_TOKEN, response);
    }
}
