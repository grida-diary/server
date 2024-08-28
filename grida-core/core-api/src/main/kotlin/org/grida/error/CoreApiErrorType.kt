package org.grida.error

import org.grida.http.BAD_REQUEST

sealed interface CoreApiErrorType : ErrorType

data object NotSupportedLoginPlatform : CoreApiErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "AUTH_PLATFORM_400_1"
    override val message: String = "지원하지 않는 로그인 플랫폼 입니다."
    override val logLevel: LogLevel = INFO
}
