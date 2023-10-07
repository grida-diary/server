package io.wwan13.domain.member.exception;

import io.wwan13.exeption.base.BaseException;

public class MemberNotFountException extends BaseException {
    public MemberNotFountException() {
        super(MemberErrorCode.MEMBER_NOT_FOUND);
    }
}
