package org.grida.auth

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.grida.api.ApiResponse
import org.grida.error.GridaException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthFilterExceptionHandler(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: GridaException) {
            val responseWriter = response.writer

            val errorResponse = ApiResponse.error(e.errorType)
            objectMapper.writeValue(responseWriter, errorResponse)
            response.status = e.errorType.httpStatusCode

            responseWriter.flush()
        }
    }
}
