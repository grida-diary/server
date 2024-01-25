package org.grida.exceptionhandler;

import org.grida.response.ApiResponse;
import org.grida.exception.BaseException;
import org.grida.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;

import static org.grida.exceptionhandler.GlobalErrorCode.*;

@RestControllerAdvice
public class ApIExceptionHandler {

    // validate errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorCode errorCode = INVALID_HTTP_MESSAGE_BODY;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.error(getCause(e), errorCode.name(), errorCode.getMessage()));
    }

    // binding errors
    @ExceptionHandler(BindException.class)
    private ResponseEntity<ApiResponse> handleBindException(BindException e) {
        ErrorCode errorCode = INVALID_HTTP_MESSAGE_BODY;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.error(getCause(e), errorCode.name(), errorCode.getMessage()));
    }

    // enum type binding error
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        ErrorCode errorCode = INVALID_HTTP_MESSAGE_BODY;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.error(getCause(e), errorCode.name(), errorCode.getMessage()));
    }

    // unsupported http method error
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ResponseEntity<ApiResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        ErrorCode errorCode = UNSUPPORTED_HTTP_METHOD;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.error(getCause(e), errorCode.name(), errorCode.getMessage()));
    }

    // cannot read http request
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        ErrorCode errorCode = INVALID_HTTP_REQUEST;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.error(getCause(e), errorCode.name(), errorCode.getMessage()));
    }

    // http 404 not found
    @ExceptionHandler(NoHandlerFoundException.class)
    private ResponseEntity<ApiResponse> handleNoHandlerFoundException(
            NoHandlerFoundException e) {
        ErrorCode errorCode = API_NOT_FOUND;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.error(getCause(e), errorCode.name(), errorCode.getMessage()));
    }

    // business logic error
    @ExceptionHandler(BaseException.class)
    private ResponseEntity<ApiResponse> handleBusinessException(BaseException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.internalServerError()
                .body(ApiResponse.error(getCause(e), errorCode.name(), e.getErrorMessage()));
    }

    // left
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiResponse> handleException(Exception e) {
        ErrorCode errorCode = SERVER_ERROR;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.error(getCause(e), errorCode.name(), errorCode.getMessage()));
    }

    private String getCause(Exception e) {
        return e.getClass().getSimpleName();
    }
}
