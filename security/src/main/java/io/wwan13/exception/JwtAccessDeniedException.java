package io.wwan13.exception;

import io.wwan13.exeption.base.ErrorCode;

import javax.servlet.http.HttpServletResponse;

public class JwtAccessDeniedException extends SecurityException {
    public JwtAccessDeniedException(HttpServletResponse response) {
        super(SecurityErrorCode.ACCESS_DENIED, response);
    }
}
