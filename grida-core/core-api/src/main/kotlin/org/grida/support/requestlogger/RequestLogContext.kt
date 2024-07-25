package org.grida.support.requestlogger

import org.springframework.http.HttpStatus
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

fun ContentCachingRequestWrapper.getRequestHeaders(): String {
    val headers = this.headerNames.toList().map { "\"$it\":\"${this.getHeader(it)}\"" }
    val joinedHeaders = headers.joinToString(", ")
    return "[$joinedHeaders]"
}

fun ContentCachingRequestWrapper.getRequestParams(): String {
    val params = this.parameterNames.toList().map { "\"$it\":\"${this.getParameter(it)}\"" }
    val joinedHeaders = params.joinToString(", ")
    return "[$joinedHeaders]"
}

fun String.trimSpaceAndNewLine(): String {
    return this.replace("\\n".toRegex(), "").replace(" ", "")
}

data class RequestLogContext(
    val method: String,
    val uri: String,
    val status: HttpStatus,
    val elapsed: Double,
    val requestHeaders: String,
    val requestParams: String,
    val requestBody: String,
    val responseBody: String
) {

    fun toLogMessage(): String {
        return """
            |
            |$method $uri - $status ($elapsed s)
            |>> REQUEST HEADERS : $requestHeaders
            |>> REQUEST PARAMS : $requestParams
            |>> REQUEST BODY : $requestBody
            |>> RESPONSE BODY : $responseBody
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
                requestHeaders = request.getRequestHeaders(),
                requestParams = request.getRequestParams(),
                requestBody = String(request.contentAsByteArray).trimSpaceAndNewLine(),
                responseBody = String(response.contentAsByteArray).trimSpaceAndNewLine(),
            )
        }
    }
}