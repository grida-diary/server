package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

import javax.servlet.http.HttpServletResponse;

public class InvalidJwtTokenException extends SecurityException {
    public InvalidJwtTokenException(HttpServletResponse response) {
        super(SecurityErrorCode.INVALID_JWT_TOKEN, response);
    }
}
