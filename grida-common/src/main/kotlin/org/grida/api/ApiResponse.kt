package org.grida.api

import org.grida.api.dto.ErrorResponse
import org.grida.datetime.DateTimePicker
import org.grida.datetime.withDefaultFormat
import org.grida.exception.GridaException

data class ApiResponse<T> private constructor(
    val status: ApiStatus,
    val data: T? = null,
    val timestamp: String = DateTimePicker.now().withDefaultFormat(),
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> = ApiResponse(ApiStatus.SUCCESS, data)

        fun success(): ApiResponse<Any> = ApiResponse(ApiStatus.SUCCESS)

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
