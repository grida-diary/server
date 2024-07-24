package org.grida.api.dto

data class ErrorResponse(
    val errorCode: String,
    val message: String,
)
