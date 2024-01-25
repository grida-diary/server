package org.grida.exception;

public class AwsS3ClientException extends BaseException {

    public AwsS3ClientException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
