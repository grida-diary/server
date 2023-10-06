package io.wwan13.domain.member.exception;

import io.wwan13.exeption.base.BaseException;

public class AgeValueErrorException extends BaseException {
    public AgeValueErrorException() {
        super(MemberErrorCode.AGE_VALUE_ERROR);
    }
}
