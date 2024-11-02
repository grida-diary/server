package org.grida.auth

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.grida.api.ApiResponse
import org.grida.error.Forbidden
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class ForbiddenHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException,
    ) {
        response.contentType = "application/json; charset=UTF-8"
        response.characterEncoding = "UTF-8"

        val responseWriter = response.writer
        val errorResponse = ApiResponse.error(Forbidden)
        objectMapper.writeValue(responseWriter, errorResponse)
        response.status = 403
        responseWriter.flush()
    }
}
