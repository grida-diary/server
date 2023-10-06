package io.wwan13.response;

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

    public static <T> SuccessResponse of(int httpStatusCode, T data) {
        return new SuccessResponse(httpStatusCode, data, null);
    }

    public static <T> SuccessResponse of(int httpStatusCode, T data, String message) {
        return new SuccessResponse(httpStatusCode, data, message);
    }

    public static <T> SuccessResponse of(int httpStatusCode, String message) {
        return new SuccessResponse(httpStatusCode, null, message);
    }
}
