package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

import javax.servlet.http.HttpServletResponse;

public class NoValidJwtTokensException extends SecurityException {
    public NoValidJwtTokensException(HttpServletResponse response) {
        super(SecurityErrorCode.NO_VALID_JWT_TOKENS, response);
    }
}
