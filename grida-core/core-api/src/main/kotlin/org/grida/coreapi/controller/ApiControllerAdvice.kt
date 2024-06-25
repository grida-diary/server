package org.grida.coreapi.controller

import org.grida.api.ApiResponse
import org.grida.api.ErrorResponse
import org.grida.exception.GridaException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {

    fun handleGridaException(
        e: GridaException
    ): ResponseEntity<ErrorResponse> {
        val response = ApiResponse.error(e)
        return ResponseEntity.status(e.httpStatusCode).body(response)
    }
}
