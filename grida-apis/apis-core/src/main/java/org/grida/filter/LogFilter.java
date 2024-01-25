package org.grida.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Component
@Slf4j
public class LogFilter extends OncePerRequestFilter {

    private static final String EMPTY_AUTHENTICATION_NAME = "Anonymous User";
    private static final List<String> CLIENT_IP_HEADERS = List.of("X-Forwarded-For", "Proxy-Client-IP",
            "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR");

    @Override
    public void doFilterInternal(HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse,
                                 FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(httpServletResponse);

        filterChain.doFilter(wrappedRequest, wrappedResponse);

        String clientIp = getClientIP(wrappedRequest);
        logHttpRequest(wrappedRequest, clientIp);
        logHttpResponse(wrappedResponse, clientIp);
        wrappedResponse.copyBodyToResponse();
    }

    private void logHttpRequest(ContentCachingRequestWrapper request, String clientIp) {
        log.info("[Request  ({})] Method:'{}', Uri:'{}', User:'{}', Body:'{}'",
                clientIp,
                request.getMethod(),
                request.getRequestURI(),
                getPrincipalName(request.getUserPrincipal()),
                new String(request.getContentAsByteArray()));
    }

    private void logHttpResponse(ContentCachingResponseWrapper response, String clientIp) {
        log.info("[Response ({})] Status:'{}', Body:'{}''",
                clientIp,
                response.getStatus(),
                new String(response.getContentAsByteArray()));
    }

    private String getPrincipalName(Principal principal) {
        if (principal == null) {
            return EMPTY_AUTHENTICATION_NAME;
        }
        return principal.getName();
    }

    private String getClientIP(ContentCachingRequestWrapper request) {
        for (String header : CLIENT_IP_HEADERS) {
            String ip = request.getHeader(header);
            if (StringUtils.hasText(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
}
