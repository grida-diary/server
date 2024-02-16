package org.grida.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.grida.http.HttpStatusCode.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum SsoAppErrorCode implements ErrorCode {

    PASSWORD_CHECK_NOT_MATCHES(BAD_REQUEST, "비밀번호 확인이 일치하지 않습니다."),
    PASSWORD_NOT_MATCHES(BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    INVALID_EMAIL(BAD_REQUEST, "유효하지 않은 이메일 입니다.");

    private final int httpStatus;
    private final String message;
}
