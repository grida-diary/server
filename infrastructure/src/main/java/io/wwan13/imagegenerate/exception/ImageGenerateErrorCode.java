package io.wwan13.imagegenerate.exception;

import io.wwan13.constant.HttpStatusCode;
import io.wwan13.exeption.base.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageGenerateErrorCode implements ErrorCode {

    CANNOT_PARSING_RESPONSE(HttpStatusCode.INTERNAL_SERVER_ERROR, "응답을 처리하는데 실패하였습니다."),

    INVALID_NUMBER_OF_IMAGES(HttpStatusCode.INTERNAL_SERVER_ERROR, "이미지 개수는 1 미만일 수 없습니다."),

    INVALID_PREFIX_REGEX(HttpStatusCode.INTERNAL_SERVER_ERROR, "프롬프트 REGEX는 #으로 시작해야 합니다."),
    REGEX_CANNOT_BE_LOWER_CASE(HttpStatusCode.INTERNAL_SERVER_ERROR, "프롬프트 REGEX는 소문자일 수 없습니다."),
    INVALID_SIZE_REGEX(HttpStatusCode.INTERNAL_SERVER_ERROR, "프롬프트 REGEX는 최소 2자 이상이여야 합니다."),

    REGEX_NOT_CONTAINS(HttpStatusCode.INTERNAL_SERVER_ERROR, "프롬프트에 REGEX가 포함되어 있지 않습니다."),
    ;

    private final int httpStatus;
    private final String message;
}
