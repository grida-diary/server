package org.grida.error

import org.grida.http.BAD_REQUEST
import org.grida.http.FORBIDDEN
import org.grida.http.UNAUTHORIZED

sealed interface CoreApiErrorType : ErrorType

data object NotSupportedLoginPlatform : CoreApiErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "AUTH_PLATFORM_400_1"
    override val message: String = "지원하지 않는 로그인 플랫폼 입니다."
    override val logLevel: LogLevel = INFO
}

data object Unauthorized : CoreApiErrorType {
    override val httpStatusCode: Int = UNAUTHORIZED
    override val errorCode: String = "CORE_API_401"
    override val message: String = "유효한 인증 정보가 없습니다."
    override val logLevel: LogLevel = INFO
}

data object Forbidden : CoreApiErrorType {
    override val httpStatusCode: Int = FORBIDDEN
    override val errorCode: String = "CORE_API_403"
    override val message: String = "접근 권한이 없습니다."
    override val logLevel: LogLevel = INFO
}

data object InvalidJwtToken : CoreApiErrorType {
    override val httpStatusCode: Int = UNAUTHORIZED
    override val errorCode: String = "CORE_API_401_1"
    override val message: String = "잟못된 인증토큰 입니다."
    override val logLevel: LogLevel = INFO
}

data object ExpiredJwtToken : CoreApiErrorType {
    override val httpStatusCode: Int = UNAUTHORIZED
    override val errorCode: String = "CORE_API_401_2"
    override val message: String = "만료 인증토큰 입니다."
    override val logLevel: LogLevel = INFO
}
