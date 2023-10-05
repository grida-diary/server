package io.wwan13.common.exeption.global;

import io.wwan13.common.exeption.base.BaseException;
import io.wwan13.common.exeption.base.ErrorCode;

public class UnsupportedHttpMethodException extends BaseException {
    public UnsupportedHttpMethodException() {
        super(GlobalErrorCode.UNSUPPORTED_HTTP_METHOD);
    }
}
