package io.wwan13.domain.member.exception;

import io.wwan13.exeption.base.BaseException;

public class EmailFormatErrorException extends BaseException {
    public EmailFormatErrorException() {
        super(MemberErrorCode.EMAIL_FORMAT_ERROR);
    }
}
