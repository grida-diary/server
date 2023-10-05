package io.wwan13.core.domain.member.exception;

import io.wwan13.common.exeption.base.BaseException;
import io.wwan13.common.exeption.base.ErrorCode;

public class AgeValueErrorException extends BaseException {
    public AgeValueErrorException() {
        super(MemberErrorCode.AGE_VALUE_ERROR);
    }
}
