package org.grida.api

import org.grida.exception.GridaException
import java.time.LocalDateTime

open class ApiResponse(
    val status: ApiStatus,
    val dateTime: LocalDateTime? = LocalDateTime.now(),
) {
    companion object {
        fun <T> success(data: T): SuccessResponse<T> = SuccessResponse(data = data)

        fun <T> success(
            data: T,
            message: String,
        ): SuccessResponse<T> = SuccessResponse(data, message)

        fun <T> success(message: String): SuccessResponse<T> = SuccessResponse(message = message)

        fun <T> success(): SuccessResponse<T> = SuccessResponse()

        fun error(
            errorCode: String,
            message: String,
        ): ErrorResponse = ErrorResponse(errorCode, message)

        fun error(exception: GridaException): ErrorResponse = ErrorResponse(exception.errorCode, exception.message)
    }
}

enum class ApiStatus {
    SUCCESS,
    ERROR,
}

data class SuccessResponse<T>(
    val data: T? = null,
    val message: String? = "Api 호출에 성공하였습니다.",
) : ApiResponse(
        ApiStatus.SUCCESS,
    )

data class ErrorResponse(
    val errorCode: String,
    val message: String? = "Api 호출에 실패하였습니다.",
) : ApiResponse(
        ApiStatus.ERROR,
    )
