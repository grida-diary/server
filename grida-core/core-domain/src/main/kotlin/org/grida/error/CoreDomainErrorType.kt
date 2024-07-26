package org.grida.error

import org.grida.http.BAD_REQUEST
import org.grida.http.FORBIDDEN

sealed interface CoreDomainErrorType : ErrorType

data object NoSuchData : CoreDomainErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "CORE_DOMAIN_400_1"
    override val message: String = "찾으려는 데이터가 없습니다."
    override val logLevel: LogLevel = INFO
}

data object AccessFailed : CoreDomainErrorType {
    override val httpStatusCode: Int = FORBIDDEN
    override val errorCode: String = "CORE_DOMAIN_403_1"
    override val message: String = "접근 권한이 없습니다."
    override val logLevel: LogLevel = INFO
}

data object UnusableUsername : CoreDomainErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "USER_400_1"
    override val message: String = "사용할 수 없는 username 입니다."
    override val logLevel: LogLevel = INFO
}

data object ActivateProfileImageAlreadyExists : CoreDomainErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "PROFILE_IMAGE_400_1"
    override val message: String = "이미 활성화된 프로필 이미지가 존재합니다."
    override val logLevel: LogLevel = INFO
}

data object PasswordConfirmNotMatched : CoreDomainErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "AUTH_400_1"
    override val message: String = "비밀번호와 비밀번호 확인이 일치하지 않습니다."
    override val logLevel: LogLevel = INFO
}

data object CannotAppendDiaryAtDate : CoreDomainErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "DIARY_400_1"
    override val message: String = "해당 날짜에 존재하는 일기가 있습니다."
    override val logLevel: LogLevel = INFO
}

data object CannotAppendDiaryAtFuture : CoreDomainErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "DIARY_400_2"
    override val message: String = "미래의 일기를 작성할 수 없습니다."
    override val logLevel: LogLevel = INFO
}
