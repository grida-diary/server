package io.wwan13.storage.exception;

import io.wwan13.exeption.base.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static io.wwan13.constant.HttpStatusCode.INTERNAL_SERVER_ERROR;

@Getter
@RequiredArgsConstructor
public enum StorageErrorCode implements ErrorCode {

    WRONG_IMAGE_URL(INTERNAL_SERVER_ERROR, "올바르지 않은 이미지 URL 입니다."),
    CANNOT_READ_IMAGE(INTERNAL_SERVER_ERROR, "이미지를 읽을 수 없습니다.");

    private final int httpStatus;
    private final String message;
}
