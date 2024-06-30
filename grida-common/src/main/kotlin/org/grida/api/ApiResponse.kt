package org.grida.api

import org.grida.exception.GridaException
import java.time.LocalDateTime

data class ApiResponse<T> private constructor(
    val status: ApiStatus,
    val data: T? = null,
    val timestamp: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> = ApiResponse(ApiStatus.SUCCESS, data)

        fun success(): ApiResponse<Any> = ApiResponse(ApiStatus.SUCCESS)

        fun id(id: Long): ApiResponse<IdResponse> = ApiResponse(ApiStatus.SUCCESS, IdResponse(id))

        fun error(exception: GridaException): ApiResponse<ErrorResponse> =
            ApiResponse(ApiStatus.ERROR, ErrorResponse(exception.errorCode, exception.message))

        fun error(errorCode: String, message: String): ApiResponse<ErrorResponse> =
            ApiResponse(ApiStatus.ERROR, ErrorResponse(errorCode, message))
    }
}

enum class ApiStatus {
    SUCCESS,
    ERROR,
}

data class IdResponse(
    val id: Long,
)

data class ErrorResponse(
    val errorCode: String,
    val message: String,
)
