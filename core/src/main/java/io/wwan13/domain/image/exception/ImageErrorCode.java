package io.wwan13.domain.image.exception;

import io.wwan13.constant.HttpStatusCode;
import io.wwan13.exeption.base.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageErrorCode implements ErrorCode {

    IMAGE_NOT_FOUND(HttpStatusCode.BAD_REQUEST, "이미지를 찾을 수 없습니다."),
    NOT_VALID_URL_PREFIX(HttpStatusCode.BAD_REQUEST, "이미지의 출처가 정확하지 않습니다.")
    ;

    private final int httpStatus;
    private final String message;
}
