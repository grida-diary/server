package org.grida.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.grida.http.HttpStatusCode.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum EndUserErrorCode implements ErrorCode {

    ALREADY_COMPLETE_ONBOARDING(BAD_REQUEST, "이미 온보딩을 완료한 사용자 입니다."),
    DO_ONBOARDING_FIRST(BAD_REQUEST, "온보딩 과정을 먼저 수행해 주세요");

    private final int httpStatus;
    private final String message;
}
