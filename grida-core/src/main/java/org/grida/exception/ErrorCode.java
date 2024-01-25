package org.grida.exception;

public interface ErrorCode {

    int getHttpStatus();

    String name();

    String getMessage();
}
