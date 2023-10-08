package io.wwan13.domain.member.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class PasswordCheckFailException extends BaseException {
    public PasswordCheckFailException() {
        super(MemberErrorCode.PASSWORD_CHECK_FAIL);
    }
}
