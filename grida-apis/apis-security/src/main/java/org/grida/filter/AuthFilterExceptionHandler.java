package org.grida.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.grida.exception.ApisSecurityException;
import org.grida.exception.ErrorCode;
import org.grida.response.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilterExceptionHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ApisSecurityException exception) {
            setErrorResponse(exception.getErrorCode(), response);
        }
    }

    private void setErrorResponse(ErrorCode errorCode,
                                  HttpServletResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getHttpStatus());

        try {
            ApiResponse errorResponse = ApiResponse.error(errorCode, "ApiSecurityException");
            String jsonResponse = objectMapper.writeValueAsString(errorResponse);
            response.getWriter().write(jsonResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
