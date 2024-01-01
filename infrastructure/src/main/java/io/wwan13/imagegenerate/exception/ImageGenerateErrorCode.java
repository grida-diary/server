package io.wwan13.imagegenerate.exception;

import io.wwan13.constant.HttpStatusCode;
import io.wwan13.exeption.base.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageGenerateErrorCode implements ErrorCode {

    CANNOT_PARSING_RESPONSE(HttpStatusCode.INTERNAL_SERVER_ERROR, "응답을 처리하는데 실패하였습니다."),
    INVALID_NUMBER_OF_IMAGES(HttpStatusCode.INTERNAL_SERVER_ERROR, "이미지 개수는 1 미만일 수 없습니다.")
    ;

    private final int httpStatus;
    private final String message;
}
