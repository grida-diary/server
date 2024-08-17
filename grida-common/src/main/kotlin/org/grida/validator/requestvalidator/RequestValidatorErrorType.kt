package org.grida.validator.requestvalidator

import org.grida.error.ErrorType
import org.grida.error.INFO
import org.grida.error.LogLevel
import org.grida.http.BAD_REQUEST

sealed interface RequestValidatorErrorType : ErrorType

data class InvalidInputValue(
    override val errorCode: String,
    override val message: String,
    override val httpStatusCode: Int = BAD_REQUEST,
    override val logLevel: LogLevel = INFO
) : RequestValidatorErrorType

data object DefaultInvalidInputValue : RequestValidatorErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "HTTP_400_0"
    override val message: String = "올바르지 않은 입력값 입니다."
    override val logLevel: LogLevel = INFO
}

