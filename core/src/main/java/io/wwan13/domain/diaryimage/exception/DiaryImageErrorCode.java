package io.wwan13.domain.diaryimage.exception;

import io.wwan13.constant.HttpStatusCode;
import io.wwan13.exeption.base.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DiaryImageErrorCode implements ErrorCode {

    DIARY_IMAGE_NOT_FOUND(HttpStatusCode.BAD_REQUEST, "일기와 매칭되는 그림을 찾을 수 없습니다.")
    ;

    private final int httpStatus;
    private final String message;
}
