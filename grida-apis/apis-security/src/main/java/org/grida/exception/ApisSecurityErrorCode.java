package org.grida.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.grida.http.HttpStatusCode.*;

@Getter
@RequiredArgsConstructor
public enum ApisSecurityErrorCode implements ErrorCode {

    HTTP_UNAUTHORIZED(UNAUTHORIZED, "인증되지 않은 사용자 입니다."),
    HTTP_FORBIDDEN(FORBIDDEN, "접근 권한이 없습니다."),

    INVALID_TOKEN(UNAUTHORIZED, "유효하지 않은 인증 토큰 입니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, "만료된 인증 토큰 입니다."),
    UNSUPPORTED_TOKEN(UNAUTHORIZED, "지원하지 않는 인증 토큰 입니다."),
    WRONG_TOKEN(UNAUTHORIZED, "잘못된 인증 토큰입니다."),

    NO_AUTHENTICATED_USER(UNAUTHORIZED, "인증된 유저가 없습니다.");

    private final int httpStatus;
    private final String message;
}
