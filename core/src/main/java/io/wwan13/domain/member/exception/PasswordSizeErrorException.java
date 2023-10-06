package io.wwan13.domain.member.exception;

import io.wwan13.exeption.base.BaseException;

public class PasswordSizeErrorException extends BaseException {
    public PasswordSizeErrorException() {
        super(MemberErrorCode.PASSWORD_SIZE_ERROR);
    }
}
