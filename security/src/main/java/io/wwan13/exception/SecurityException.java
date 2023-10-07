package io.wwan13.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wwan13.exeption.base.ErrorCode;
import io.wwan13.response.ErrorResponse;

import javax.servlet.http.HttpServletResponse;

public abstract class SecurityException {

    private final ErrorCode errorCode;
    private final HttpServletResponse response;

    public SecurityException(ErrorCode errorCode, HttpServletResponse response) {
        this.errorCode = errorCode;
        this.response = response;
        commence();
    }

    private void commence() {
        response.setStatus(errorCode.getHttpStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(ErrorResponse.of(errorCode));
            response.getWriter().write(json);
        } catch (Exception e) {

        }
    }

}
