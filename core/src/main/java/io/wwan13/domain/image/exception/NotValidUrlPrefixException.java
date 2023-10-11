package io.wwan13.domain.image.exception;

import io.wwan13.exeption.base.BaseException;
import io.wwan13.exeption.base.ErrorCode;

public class NotValidUrlPrefixException extends BaseException {

    public NotValidUrlPrefixException() {
        super(ImageErrorCode.NOT_VALID_URL_PREFIX);
    }
}
