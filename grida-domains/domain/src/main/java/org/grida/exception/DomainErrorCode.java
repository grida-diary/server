package org.grida.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.grida.http.HttpStatusCode.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum DomainErrorCode implements ErrorCode {

    ALREADY_REGISTERED_EMAIL(BAD_REQUEST, "이미 등록된 이메일입니다."),
    INVALID_GENDER(BAD_REQUEST, "잘못된 성별입니다."),

    PROFILE_IMAGE_ALREADY_EXIST(BAD_REQUEST, "해당하는 유저의 프로필 이미지가 이미 존재합니다."),
    CANNOT_REFRESH_PROFILE_IMAGE(BAD_REQUEST, "%d월 %d일 이후에 프로필 이미지를 새로고침할 수 있습니다.");

    private final int httpStatus;
    private final String message;
}
