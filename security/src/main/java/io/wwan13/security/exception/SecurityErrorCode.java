package io.wwan13.security.exception;

import io.wwan13.common.constant.HttpStatusCode;
import io.wwan13.common.exeption.base.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SecurityErrorCode implements ErrorCode {

    ACCESS_DENIED(HttpStatusCode.FORBIDDEN, "권한이 일치하지 않습니다."),
    AUTHENTICATION_ENTRY_POINT(HttpStatusCode.UNAUTHORIZED, "유효한 자격이 아닙니다."),

    INVALID_JWT_TOKEN(HttpStatusCode.UNAUTHORIZED, "지원되지 않는 JWT 토큰 입니다."),
    EXPIRED_JWT_TOKEN(HttpStatusCode.UNAUTHORIZED, "만료된 JWT 토큰 입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatusCode.UNAUTHORIZED, "지원되지 않는 JWT 토큰 입니다."),
    WRONG_JWT_TOKEN(HttpStatusCode.UNAUTHORIZED, "잘못된 JWT 토큰 입니다."),
    NO_VALID_JWT_TOKENS(HttpStatusCode.UNAUTHORIZED, "유효한 JWT 토큰 이 없습니다.")
    ;

    private final int httpStatus;
    private final String message;
}
