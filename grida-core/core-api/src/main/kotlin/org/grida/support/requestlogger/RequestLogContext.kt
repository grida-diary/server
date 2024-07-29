package org.grida.support.requestlogger

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.util.Enumeration

data class RequestLogContext(
    val method: String,
    val uri: String,
    val status: HttpStatus,
    val elapsed: Double,
    val requestHeaders: Map<String, String>,
    val requestParams: Map<String, String>,
    val requestBody: String,
    val responseBody: String
) {

    fun toLogMessage(objectMapper: ObjectMapper): String {
        return """
            |
            |$method $uri - $status ($elapsed s)
            |>> REQUEST HEADERS : $requestHeaders
            |>> REQUEST PARAMS : $requestParams
            |>> REQUEST BODY : ${objectMapper.readTree(requestBody.ifBlank { "{}" })}
            |>> RESPONSE BODY : ${objectMapper.readTree(responseBody)}
        """.trimMargin()
    }

    companion object {
        fun of(
            request: ContentCachingRequestWrapper,
            response: ContentCachingResponseWrapper,
            elapsed: Double
        ): RequestLogContext {
            return RequestLogContext(
                method = request.method,
                uri = request.requestURI,
                status = HttpStatus.valueOf(response.status),
                elapsed = elapsed,
                requestHeaders = extractAsMap(request, request.headerNames),
                requestParams = extractAsMap(request, request.parameterNames),
                requestBody = String(request.contentAsByteArray),
                responseBody = String(response.contentAsByteArray),
            )
        }

        private fun extractAsMap(
            request: ContentCachingRequestWrapper,
            names: Enumeration<String>
        ): Map<String, String> {
            val result = mutableMapOf<String, String>()
            names.asIterator().forEach {
                result.put(it, request.getHeader(it))
            }
            return result
        }
    }
}
