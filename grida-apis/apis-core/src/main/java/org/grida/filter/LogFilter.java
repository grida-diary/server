package org.grida.filter;

import org.grida.logger.HttpRequestLogger;
import org.grida.logger.HttpResponseLogger;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(httpServletResponse);

        filterChain.doFilter(wrappedRequest, wrappedResponse);

        HttpRequestLogger.log(wrappedRequest);
        HttpResponseLogger.log(wrappedResponse);
        wrappedResponse.copyBodyToResponse();
    }
}
