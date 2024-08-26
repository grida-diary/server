package org.grida.config

import io.wwan13.springrequestlogger.configure.EnableLoggingRequest
import io.wwan13.springrequestlogger.configure.LogMessageConfigurer
import org.springframework.context.annotation.Configuration

@Configuration
@EnableLoggingRequest
class RequestLoggingConfig : LogMessageConfigurer {

    override fun excludePathPatterns(): List<String> {
        return listOf("/api/actuator/**")
    }
}
