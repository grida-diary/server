package io.wwan13.response;

import io.wwan13.constant.HttpStatusCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SuccessResponse<T> extends ApiResponse {

    private final T data;

    public SuccessResponse(int httpStatusCode, T data, String message) {
        super(true, httpStatusCode, message);
        this.data = data;
    }

    public static <T> SuccessResponse ok(T data) {
        return new SuccessResponse(HttpStatusCode.OK, data, null);
    }

    public static <T> SuccessResponse ok(T data, String message) {
        return new SuccessResponse(HttpStatusCode.OK, data, message);
    }

    public static <T> SuccessResponse ok(String message) {
        return new SuccessResponse(HttpStatusCode.OK, null, message);
    }

    public static <T> SuccessResponse created(T data) {
        return new SuccessResponse(HttpStatusCode.CREATED, data, null);
    }

    public static <T> SuccessResponse created(T data, String message) {
        return new SuccessResponse(HttpStatusCode.CREATED, data, message);
    }

    public static <T> SuccessResponse created(String message) {
        return new SuccessResponse(HttpStatusCode.CREATED, null, message);
    }
}
