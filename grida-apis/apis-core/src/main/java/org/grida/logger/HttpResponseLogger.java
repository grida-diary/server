package org.grida.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
public class HttpResponseLogger {

    public static void log(ContentCachingResponseWrapper response) {
        defaultResponseLog(response, response.getContentAsByteArray());
    }

    public static void log(ContentCachingResponseWrapper response, String responseBody) {
        defaultResponseLog(response, responseBody.getBytes());
    }

    private static void defaultResponseLog(ContentCachingResponseWrapper response, byte[] responseBody) {
        log.info("\n\n======[Response]======\n" +
                        "Status: {}\n" +
                        "Body:\n{}\n" +
                        "======================\n",
                response.getStatus(),
                LoggingUtils.toPrettyJson(responseBody)
        );
    }
}
