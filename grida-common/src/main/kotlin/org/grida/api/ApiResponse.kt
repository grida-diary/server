package org.grida.api

import org.grida.api.dto.ErrorResponse
import org.grida.datetime.DateTimePicker
import org.grida.datetime.DateTimeUtil
import org.grida.error.ErrorType

data class ApiResponse<T> private constructor(
    val status: ApiStatus,
    val data: T? = null,
    val timestamp: String = DateTimeUtil.toDefaultDateTimeFormat(DateTimePicker.now()),
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> = ApiResponse(ApiStatus.SUCCESS, data)

        fun success(): ApiResponse<Any> = ApiResponse(ApiStatus.SUCCESS)

        fun error(errorType: ErrorType): ApiResponse<ErrorResponse> =
            ApiResponse(ApiStatus.ERROR, ErrorResponse(errorType.errorCode, errorType.message))

        fun error(errorCode: String, message: String): ApiResponse<ErrorResponse> =
            ApiResponse(ApiStatus.ERROR, ErrorResponse(errorCode, message))
    }
}

enum class ApiStatus {
    SUCCESS,
    ERROR,
}
