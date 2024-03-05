package org.grida.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.grida.http.HttpStatusCode.INTERNAL_SERVER_ERROR;

@Getter
@RequiredArgsConstructor
public enum StorageClientErrorCode implements ErrorCode {

    INVALID_IMAGE_URL(INTERNAL_SERVER_ERROR, "지원하는 이미지 URL 형식이 아닙니다."),
    CANNOT_READ_IMAGE(INTERNAL_SERVER_ERROR, "URL 에서 이미지를 읽을 수 없습니다."),
    INVALID_DIRECTORY_FORMAT(INTERNAL_SERVER_ERROR, "파일 경로 형식은 '%s'로 입력해 주세요."),
    IMAGE_UPLOAD_FAIL(INTERNAL_SERVER_ERROR, "이미지 업로드에 실패하였습니다.");

    private final int httpStatus;
    private final String message;
}
