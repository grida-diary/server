package org.grida.error

import org.grida.http.BAD_REQUEST
import org.grida.http.FORBIDDEN
import kotlin.reflect.KClass

sealed interface CoreDomainErrorType : ErrorType

data class NoSuchData<T : Any, R>(
    val from: KClass<T>,
    val id: R
) : CoreDomainErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "CORE_DOMAIN_400_1"
    override val message: String = "찾으려는 데이터가 없습니다. ($from-$id)"
    override val logLevel: LogLevel = INFO
}

class InvalidEnumValue(
    enumValues: List<String>
) : CoreDomainErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "CORE_DOMAIN_400_2"
    override val message: String =
        "유효하지 않은 enum value 입니다.[${enumValues.joinToString("|")}]"
    override val logLevel: LogLevel = INFO
}

data object AccessFailed : CoreDomainErrorType {
    override val httpStatusCode: Int = FORBIDDEN
    override val errorCode: String = "CORE_DOMAIN_403_1"
    override val message: String = "접근 권한이 없습니다."
    override val logLevel: LogLevel = INFO
}

data object AlreadyRegisteredUser : CoreDomainErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "USER_400_1"
    override val message: String = "이미 등록된 유저입니다."
    override val logLevel: LogLevel = INFO
}

data object ActivateImageAlreadyExists : CoreDomainErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "IMAGE_400_1"
    override val message: String = "이미 활성화된 이미지가 존재합니다."
    override val logLevel: LogLevel = INFO
}

data object ImageGenerateAttemptOver : CoreDomainErrorType {
    override val httpStatusCode: Int = BAD_REQUEST
    override val errorCode: String = "IMAGE_400_2"
    override val message: String = "이미지 생성 시도 횟수를 초과하였습니다."
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
