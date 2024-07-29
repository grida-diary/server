package org.grida.support.requestlogger

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private val log = KotlinLogging.logger {}

class LogFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestWrapper = ContentCachingRequestWrapper(request)
        val responseWrapper = ContentCachingResponseWrapper(response)

        val requestOccurred = System.currentTimeMillis()
        filterChain.doFilter(requestWrapper, responseWrapper)
        val requestCompleted = System.currentTimeMillis()
        val requestElapsed = (requestCompleted - requestOccurred) / 1000.0

        val logContext = RequestLogContext.of(requestWrapper, responseWrapper, requestElapsed)
        log.info(logContext.toLogMessage(objectMapper))
        responseWrapper.copyBodyToResponse()
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.requestURI.startsWith("/api/actuator")
    }
}
