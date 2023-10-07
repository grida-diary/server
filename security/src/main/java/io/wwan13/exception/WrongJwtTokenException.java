package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

import javax.servlet.http.HttpServletResponse;

public class WrongJwtTokenException extends SecurityException {
    public WrongJwtTokenException(HttpServletResponse response) {
        super(SecurityErrorCode.WRONG_JWT_TOKEN, response);
    }
}
