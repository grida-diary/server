package org.grida.presentation

import io.wwan13.wintersecurity.exception.forbidden.ForbiddenException
import io.wwan13.wintersecurity.exception.unauthirized.UnauthorizedException
import mu.KotlinLogging
import org.grida.api.ApiResponse
import org.grida.api.ErrorResponse
import org.grida.exception.GridaException
import org.grida.http.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

private val log = KotlinLogging.logger {}

@RestControllerAdvice
class ApiControllerAdvice {

    @ExceptionHandler(GridaException::class)
    fun handleGridaException(
        e: GridaException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        when (e.httpStatusCode) {
            INTERNAL_SERVER_ERROR -> log.error(e.message)
            else -> log.info(e.message)
        }

        val response = ApiResponse.error(e)
        return ResponseEntity.status(e.httpStatusCode).body(response)
    }

    @ExceptionHandler(ForbiddenException::class)
    fun handleForbiddenException(
        e: ForbiddenException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.info(e.message)
        val response = ApiResponse.error("HTTP_403", "접근할 수 없습니다.")
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(
        e: UnauthorizedException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.info(e.message)
        val response = ApiResponse.error("HTTP_401", "유효한 인증정보가 없습니다.")
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(
        e: HttpRequestMethodNotSupportedException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.info(e.message)
        val response = ApiResponse.error("HTTP_405", "지원하지 않는 메소드 입니다.")
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response)
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleHttpRequestMethodNotSupportedException(
        e: NoHandlerFoundException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.info(e.message)
        val response = ApiResponse.error("HTTP_404", "존재하지 않는 API 요청 입니다.")
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(
        e: HttpMessageNotReadableException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        if (e.rootCause is GridaException) {
            val exception = e.rootCause as GridaException
            log.info(exception.message)
            val response = ApiResponse.error(exception)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
        }

        log.info(e.message)
        val response = ApiResponse.error("HTTP_400", "잘못된 요청 메세지 입니다.")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(
        e: Exception
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.error(e.message)
        val response = ApiResponse.error("HTTP_500", "서버에서 알 수 없는 에러가 발생하였습니다.")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}
