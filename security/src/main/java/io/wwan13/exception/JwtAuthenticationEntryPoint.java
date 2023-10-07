package io.wwan13.exception;

import io.wwan13.exeption.base.ErrorCode;

import javax.servlet.http.HttpServletResponse;

public class JwtAuthenticationEntryPoint extends SecurityException {
    public JwtAuthenticationEntryPoint(HttpServletResponse response) {
        super(SecurityErrorCode.AUTHENTICATION_ENTRY_POINT, response);
    }
}
