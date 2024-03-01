package org.grida.validator.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.grida.exception.ErrorCode;

import static org.grida.http.HttpStatusCode.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum RequestFiledErrorCode implements ErrorCode {

    MUST_BE_REQUIRED(BAD_REQUEST, "%s 필드는 값이 없을 수 없습니다."),
    MUST_BE_LONGER_THAN(BAD_REQUEST, "%s 필드의 길이는 %d보다 길어야 합니다."),
    MUST_BE_GREATER_THAN(BAD_REQUEST, "%s 필드의 값은 %d보다 커야 합니다."),
    MUST_BE_SMALLER_THAN(BAD_REQUEST, "%s 필드는 값은 %d보다 작아야 합니다."),
    MUST_BE_BETWEEN_IN(BAD_REQUEST, "%s 필드는 값은 %d보다 크고 %d보다 작아야 합니다.");

    private final int httpStatus;
    private final String message;
}
