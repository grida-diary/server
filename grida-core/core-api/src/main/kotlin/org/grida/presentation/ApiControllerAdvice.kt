package org.grida.presentation

import org.grida.api.ApiResponse
import org.grida.api.ErrorResponse
import org.grida.exception.GridaException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class ApiControllerAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(
        e: HttpRequestMethodNotSupportedException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        val response = ApiResponse.error("HTTP_405", "지원하지 않는 메소드 입니다.")
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response)
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleHttpRequestMethodNotSupportedException(
        e: NoHandlerFoundException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        val response = ApiResponse.error("HTTP_404", "존재하지 않는 API 요청 입니다.")
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    private fun handleHttpMessageNotReadableException(
        e: HttpMessageNotReadableException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        if (e.rootCause is GridaException) {
            val exception = e.rootCause as GridaException
            val response = ApiResponse.error(exception)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
        }

        val response = ApiResponse.error("HTTP_500", "잘못된 요청 메셍지 입니다.")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(GridaException::class)
    fun handleGridaException(
        e: GridaException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        val response = ApiResponse.error(e)
        return ResponseEntity.status(e.httpStatusCode).body(response)
    }
}
