package io.wwan13.domain.diary.exception;

import io.wwan13.constant.HttpStatusCode;
import io.wwan13.exeption.base.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DiaryErrorCode implements ErrorCode {

    CONTENT_TOO_SHORT(HttpStatusCode.BAD_REQUEST, "일기의 내용이 너무 짧습니다."),
    CONTENT_TOO_LONG(HttpStatusCode.BAD_REQUEST, "일기의 내용이 너무 깁니다."),
    DIARY_NOT_FOUND(HttpStatusCode.BAD_REQUEST, "일기를 찾을 수 없습니다.")
    ;

    private final int httpStatus;
    private final String message;
}
