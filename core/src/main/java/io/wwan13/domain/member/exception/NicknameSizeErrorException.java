package io.wwan13.domain.member.exception;

import io.wwan13.exeption.base.BaseException;

public class NicknameSizeErrorException extends BaseException {
    public NicknameSizeErrorException() {
        super(MemberErrorCode.NICKNAME_SIZE_ERROR);
    }
}
