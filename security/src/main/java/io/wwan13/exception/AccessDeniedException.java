package io.wwan13.exception;

import io.wwan13.exeption.base.BaseException;

public class AccessDeniedException extends BaseException {
    public AccessDeniedException() {
        super(SecurityErrorCode.ACCESS_DENIED);
    }
}
