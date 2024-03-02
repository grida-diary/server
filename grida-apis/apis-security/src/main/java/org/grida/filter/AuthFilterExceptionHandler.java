package org.grida.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.grida.exception.ApisSecurityException;
import org.grida.exception.ErrorCode;
import org.grida.logger.HttpRequestLogger;
import org.grida.logger.HttpResponseLogger;
import org.grida.response.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilterExceptionHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ApisSecurityException exception) {
            String responseBody = createErrorResponse(exception.getErrorCode());
            sendErrorToClient(response, exception.getErrorCode().getHttpStatus(), responseBody);
            logRequest(request);
            logResponse(response, responseBody);
        }
    }

    private String createErrorResponse(ErrorCode errorCode) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ApiResponse errorResponse = ApiResponse.error(errorCode, "ApiSecurityException");
            return objectMapper.writeValueAsString(errorResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendErrorToClient(HttpServletResponse response, int status, String responseBody) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);

        try {
            response.getWriter().write(responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logRequest(HttpServletRequest request) {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        HttpRequestLogger.log(wrappedRequest);
    }

    private void logResponse(HttpServletResponse response, String responseBody) {
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
        HttpResponseLogger.log(wrappedResponse, responseBody);
    }
}
