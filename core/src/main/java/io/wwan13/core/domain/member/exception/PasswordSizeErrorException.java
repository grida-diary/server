package io.wwan13.core.domain.member.exception;

import io.wwan13.common.exeption.base.BaseException;
import io.wwan13.common.exeption.base.ErrorCode;

public class PasswordSizeErrorException extends BaseException {
    public PasswordSizeErrorException() {
        super(MemberErrorCode.PASSWORD_SIZE_ERROR);
    }
}
