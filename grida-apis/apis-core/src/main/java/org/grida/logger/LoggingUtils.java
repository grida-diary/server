package org.grida.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.util.Iterator;
import java.util.Map;

public class LoggingUtils {

    private static final String EMPTY_DATA = "none";
    private static final String MAP_LOGGING_FORMAT = "%s=%s, ";
    private static final String JOIN_DELIMITER = ", ";
    private static final String JWT_TOKEN_KEY = "authorization";
    private static final int JWT_TOKEN_STARTS_INDEX = 7;

    public static String toPrettyJson(byte[] byteData) {
        String jsonData = new String(byteData);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object data = objectMapper.readValue(jsonData, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            return EMPTY_DATA;
        }
    }

    public static String toLoggableParameters(ContentCachingRequestWrapper request) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String[]> parameters = request.getParameterMap();

        if (parameters.isEmpty()) {
            return EMPTY_DATA;
        }

        parameters.keySet().forEach(key ->
                stringBuilder.append(
                        String.format(MAP_LOGGING_FORMAT, key, String.join(JOIN_DELIMITER, parameters.get(key)))
                )
        );

        return stringBuilder.toString();
    }

    public static String getJwtTokenInHeader(ContentCachingRequestWrapper request) {
        try {
            return request.getHeader(JWT_TOKEN_KEY).substring(JWT_TOKEN_STARTS_INDEX);
        } catch (NullPointerException e) {
            return EMPTY_DATA;
        }
    }
}
