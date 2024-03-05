package org.grida.exception;

public class StorageClientException extends BaseException {

    public StorageClientException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
