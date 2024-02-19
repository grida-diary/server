package org.grida.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.grida.http.HttpStatusCode.INTERNAL_SERVER_ERROR;

@Getter
@RequiredArgsConstructor
public enum DomainImageErrorCode implements ErrorCode{

    KEYWORD_CANNOT_BE_EMPTY(INTERNAL_SERVER_ERROR, "키워드의 값이 비어있습니다."),
    PROMPT_NOT_CONTAINS_KEY(INTERNAL_SERVER_ERROR, "프롬프트에 KEY가 포함되어 있지 않습니다.");

    private final int httpStatus;
    private final String message;
}
