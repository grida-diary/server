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
    DIARY_NOT_FOUND(HttpStatusCode.BAD_REQUEST, "일기를 찾을 수 없습니다."),
    FUTURE_DIARY(HttpStatusCode.BAD_REQUEST, "일기는 오늘 혹은 과거의 일기만 작성할 수 있습니다."),
    DIARY_ALREADY_EXIST_IN_DATE(HttpStatusCode.BAD_REQUEST, "일기는 하루에 하나씩 작성할 수 있습니다."),
    USE_ALL_REFRESH_CHANCE(HttpStatusCode.BAD_REQUEST, "새로고침 기회를 모두 사용하였습니다.")
    ;

    private final int httpStatus;
    private final String message;
}
