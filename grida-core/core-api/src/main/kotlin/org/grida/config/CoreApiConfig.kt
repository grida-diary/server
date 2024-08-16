package org.grida.config

import io.wwan13.springrequestlogger.configure.EnableLoggingRequest
import io.wwan13.springrequestlogger.configure.LogMessageConfigurer
import io.wwan13.springrequestlogger.context.RequestContext
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration
import java.util.function.Function

@Configuration
@ConfigurationPropertiesScan
@EnableLoggingRequest
class CoreApiConfig : LogMessageConfigurer {

    override fun format(): Function<RequestContext, String> {
        return Function { context ->
            """
                |
                | ${context.method} '${context.uri}' - ${context.status} (${context.elapsed} s)
                | >> Request Headers : ${context.requestHeaders}
                | >> Request Params  : ${context.requestParams}
                | >> Request Body    : ${context.requestBody}
                | >> Response Body   : ${context.responseBody}
            """.trimMargin()
        }
    }
}
