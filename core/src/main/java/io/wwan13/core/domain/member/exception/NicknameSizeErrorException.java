package io.wwan13.core.domain.member.exception;

import io.wwan13.common.exeption.base.BaseException;
import io.wwan13.common.exeption.base.ErrorCode;

public class NicknameSizeErrorException extends BaseException {
    public NicknameSizeErrorException() {
        super(MemberErrorCode.NICKNAME_SIZE_ERROR);
    }
}
