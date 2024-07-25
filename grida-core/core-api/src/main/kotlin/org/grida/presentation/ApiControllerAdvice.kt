package org.grida.presentation

import io.wwan13.wintersecurity.exception.forbidden.ForbiddenException
import io.wwan13.wintersecurity.exception.unauthirized.ExpiredJwtTokenException
import io.wwan13.wintersecurity.exception.unauthirized.InvalidJwtTokenException
import io.wwan13.wintersecurity.exception.unauthirized.UnauthorizedException
import mu.KotlinLogging
import org.grida.api.ApiResponse
import org.grida.api.dto.ErrorResponse
import org.grida.error.DEBUG
import org.grida.error.ERROR
import org.grida.error.GridaException
import org.grida.error.INFO
import org.grida.error.TRACE
import org.grida.error.WARN
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
        val errorType = e.errorType

        when (errorType.logLevel) {
            TRACE -> log.trace(e.message)
            DEBUG -> log.debug(e.message)
            INFO -> log.info(e.message)
            WARN -> log.warn(e.message)
            ERROR -> log.error(e.stackTraceToString())
        }

        val response = ApiResponse.error(errorType)
        return ResponseEntity.status(errorType.httpStatusCode).body(response)
    }

    @ExceptionHandler(ForbiddenException::class)
    fun handleForbiddenException(
        e: ForbiddenException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.info(e.message)
        val response = ApiResponse.error("HTTP_403", "접근 권한이 없는 요청입니다.")
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

    @ExceptionHandler(ExpiredJwtTokenException::class)
    fun handleExpiredJwtTokenException(
        e: ExpiredJwtTokenException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.info(e.message)
        val response = ApiResponse.error("HTTP_401_1", "만료된 인증 토큰 입니다.")
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response)
    }

    @ExceptionHandler(InvalidJwtTokenException::class)
    fun handleInvalidJwtTokenException(
        e: InvalidJwtTokenException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.info(e.message)
        val response = ApiResponse.error("HTTP_401_2", "만료된 인증 토큰 입니다.")
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
            val response = ApiResponse.error(exception.errorType)
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
        log.error(e.stackTraceToString())
        val response = ApiResponse.error("HTTP_500", "서버에서 알 수 없는 에러가 발생하였습니다.")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}
