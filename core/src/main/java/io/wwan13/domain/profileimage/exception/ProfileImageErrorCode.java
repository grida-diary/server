package io.wwan13.domain.profileimage.exception;

import io.wwan13.constant.HttpStatusCode;
import io.wwan13.exeption.base.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProfileImageErrorCode implements ErrorCode {

    PROFILE_IMAGE_NOT_FOUND(HttpStatusCode.BAD_REQUEST, "프로필 이미지를 찾을 수 없습니다.")
    ;

    private final int httpStatus;
    private final String message;
}
