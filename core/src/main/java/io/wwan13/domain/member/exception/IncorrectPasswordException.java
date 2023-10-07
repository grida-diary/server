package io.wwan13.domain.member.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class IncorrectPasswordException extends BaseException {
    public IncorrectPasswordException() {
        super(MemberErrorCode.INCORRECT_PASSWORD);
    }
}
