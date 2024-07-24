package org.grida.error

import org.grida.http.BAD_REQUEST

sealed interface CoreApiErrorType : ErrorType

data object LoginFailed : CoreApiErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "AUTH_400_1"
    override val message: String = "아이디 혹은 비밀번호를 확인하세요."
    override val logLevel: LogLevel = INFO
}
