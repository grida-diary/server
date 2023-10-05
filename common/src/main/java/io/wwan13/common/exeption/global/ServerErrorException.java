package io.wwan13.common.exeption.global;

import io.wwan13.common.exeption.base.BaseException;
import io.wwan13.common.exeption.base.ErrorCode;

public class ServerErrorException extends BaseException {
    public ServerErrorException() {
        super(GlobalErrorCode.SERVER_ERROR);
    }
}
