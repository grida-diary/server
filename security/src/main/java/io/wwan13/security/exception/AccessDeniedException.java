package io.wwan13.security.exception;

import io.wwan13.common.exeption.BaseException;
import io.wwan13.common.exeption.ErrorCode;

public class AccessDeniedException extends BaseException {
    public AccessDeniedException() {
        super(SecurityErrorCode.ACCESS_DENIED);
    }
}
