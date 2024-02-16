package org.grida.validator;

import org.grida.validator.exception.RequestFieldException;
import org.springframework.util.StringUtils;

import static org.grida.validator.exception.RequestFiledErrorCode.*;

public class RequestValidator {

    private RequestValidator() {
    }

    public static void required(String fieldName, String value) {
        if (!StringUtils.hasText(value)) {
            throw new RequestFieldException(MUST_BE_REQUIRED, fieldName);
        }
    }

    public static void required(String fieldName, Object value) {
        if (value == null) {
            throw new RequestFieldException(MUST_BE_REQUIRED, fieldName);
        }
    }

    public static void isLongerThan(String fieldName, String value, int threshold) {
        required(fieldName, value);
        if (value.length() < threshold) {
            throw new RequestFieldException(MUST_BE_LONGER_THAN, fieldName, threshold);
        }
    }

    public static void isGreaterThan(String fieldName, Number value, int threshold) {
        required(fieldName, value);
        if (value.intValue() < threshold) {
            throw new RequestFieldException(MUST_BE_GREATER_THAN, fieldName, threshold);
        }
    }

    public static void isSmallerThan(String fieldName, Number value, int threshold) {
        required(fieldName, value);
        if (value.intValue() > threshold) {
            throw new RequestFieldException(MUST_BE_SMALLER_THAN, fieldName, threshold);
        }
    }

    public static void isBetweenIn(String fieldName, Number value, int startRange, int endRange) {
        required(fieldName, value);
        if (value.intValue() < startRange || value.intValue() > endRange) {
            throw new RequestFieldException(MUST_BE_LONGER_THAN, fieldName, startRange, endRange);
        }
    }
}
