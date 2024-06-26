package org.grida.api

import org.grida.exception.GridaException
import java.time.LocalDateTime

open class ApiResponse(
    val status: ApiStatus,
    val timestamp: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun <T> success(data: T): ApiResponse = SuccessResponse(data)

        fun <T> success(
            data: T,
            message: String,
        ): ApiResponse = SuccessResponse(data, message)

        fun success(): ApiResponse = SuccessResponse<String>()

        fun id(id: Long): ApiResponse = SuccessResponse(IdResponse(id))

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
) : ApiResponse(ApiStatus.SUCCESS)

data class ErrorResponse(
    val errorCode: String,
    val message: String? = "Api 호출에 실패하였습니다.",
) : ApiResponse(ApiStatus.ERROR)

data class IdResponse(
    val id: Long,
)
