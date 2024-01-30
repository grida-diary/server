package org.grida.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.grida.http.HttpStatusCode.INTERNAL_SERVER_ERROR;

@Getter
@RequiredArgsConstructor
public enum DomainAiErrorCode implements ErrorCode {

    CANNOT_PARSING_CHAT_RESPONSE(INTERNAL_SERVER_ERROR, "감정 분석 결과를 읽어오지 못하였습니다."),
    INVALID_IMAGE_COUNT(INTERNAL_SERVER_ERROR, "이미지는 %d개 이상으로 생성해야 합니다."),;

    private final int httpStatus;
    private final String message;
    }
