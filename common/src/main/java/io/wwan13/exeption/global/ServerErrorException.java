package io.wwan13.exeption.global;

import io.wwan13.exeption.base.BaseException;

public class ServerErrorException extends BaseException {
    public ServerErrorException() {
        super(GlobalErrorCode.SERVER_ERROR);
    }
}
