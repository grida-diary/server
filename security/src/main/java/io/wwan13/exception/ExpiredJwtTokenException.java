package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

import javax.servlet.http.HttpServletResponse;

public class ExpiredJwtTokenException extends SecurityException {
    public ExpiredJwtTokenException(HttpServletResponse response) {
        super(SecurityErrorCode.EXPIRED_JWT_TOKEN, response);
    }
}
