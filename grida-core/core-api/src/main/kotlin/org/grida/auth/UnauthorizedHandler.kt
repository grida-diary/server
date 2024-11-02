package org.grida.auth

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.grida.api.ApiResponse
import org.grida.error.Unauthorized
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class UnauthorizedHandler(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        response.contentType = "application/json; charset=UTF-8"
        response.characterEncoding = "UTF-8"

        val responseWriter = response.writer
        val errorResponse = ApiResponse.error(Unauthorized)
        objectMapper.writeValue(responseWriter, errorResponse)
        response.status = 401
        responseWriter.flush()
    }
}
