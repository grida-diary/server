package io.wwan13.domain.member.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class EmailAlreadyExistsException extends BaseException {
    public EmailAlreadyExistsException() {
        super(MemberErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
