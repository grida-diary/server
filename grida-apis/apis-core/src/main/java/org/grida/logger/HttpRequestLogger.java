package org.grida.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Slf4j
public class HttpRequestLogger {

    public static void log(ContentCachingRequestWrapper request) {
        log.info("\n\n======[Request]=======\n" +
                        "Method: {}\n" +
                        "Uri: {}\n" +
                        "Parameters: {}\n" +
                        "Jwt Token: {}\n" +
                        "Body:\n{}\n" +
                        "======================\n",
                request.getMethod(),
                request.getRequestURI(),
                LoggingUtils.toLoggableParameters(request),
                LoggingUtils.getJwtTokenInHeader(request),
                LoggingUtils.toPrettyJson(request.getContentAsByteArray())
        );
    }
}
