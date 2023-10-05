package io.wwan13.core.domain.member.exception;

import io.wwan13.common.exeption.base.BaseException;
import io.wwan13.common.exeption.base.ErrorCode;

public class EmailFormatErrorException extends BaseException {
    public EmailFormatErrorException() {
        super(MemberErrorCode.EMAIL_FORMAT_ERROR);
    }
}
