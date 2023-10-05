package io.wwan13.core.domain.member.exception;

import io.wwan13.common.constant.HttpStatusCode;
import io.wwan13.common.exeption.base.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    EMAIL_FORMAT_ERROR(HttpStatusCode.BAD_REQUEST, "이메일 형식이 잘못되었습니다."),
    PASSWORD_SIZE_ERROR(HttpStatusCode.BAD_REQUEST, "비밀번호는 최소 4자리 최대 12자리로 설정해 주세요."),
    NICKNAME_SIZE_ERROR(HttpStatusCode.BAD_REQUEST, "닉네임은 최소 2자 최대 12자리 설정해 주세요."),
    AGE_VALUE_ERROR(HttpStatusCode.BAD_REQUEST, "나이는 1 이하일 수 없슨디ㅏ.")
    ;

    private final int httpStatus;
    private final String message;
}
