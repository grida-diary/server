package org.grida.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.grida.http.HttpStatusCode.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum DomainRdsErrorCode implements ErrorCode {

    USER_NOT_FOUND(BAD_REQUEST, "해당하는 유저를 찾을 수 없습니다."),

    PROFILE_IMAGE_NOT_FOUND(BAD_REQUEST, "해당하는 유저의 프로필 이미지를 찾을 수 없습니다."),

    DIARY_NOT_FOUND(BAD_REQUEST, "해당하는 일기를 찾을 수 없습니다."),

    DIARY_IMAGE_NOT_FOUND(BAD_REQUEST, "해당하는 일기의 그림을 찾을 수 없습니다.");

    private final int httpStatus;
    private final String message;
}
