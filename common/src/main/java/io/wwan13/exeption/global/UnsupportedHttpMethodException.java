package io.wwan13.exeption.global;

import io.wwan13.exeption.base.BaseException;

public class UnsupportedHttpMethodException extends BaseException {
    public UnsupportedHttpMethodException() {
        super(GlobalErrorCode.UNSUPPORTED_HTTP_METHOD);
    }
}
