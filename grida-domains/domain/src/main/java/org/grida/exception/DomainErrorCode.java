package org.grida.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.grida.http.HttpStatusCode.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum DomainErrorCode implements ErrorCode {

    ALREADY_REGISTERED_EMAIL(BAD_REQUEST, "이미 등록된 이메일입니다."),
    INVALID_GENDER(BAD_REQUEST, "잘못된 성별입니다.");

    private final int httpStatus;
    private final String message;
}
