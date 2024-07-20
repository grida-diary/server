package org.grida.support.requestlogger

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean

class LogFilterRegistrar {

    @Bean
    fun logFilter(
        objectMapper: ObjectMapper
    ): FilterRegistrationBean<LogFilter> {
        val filter = FilterRegistrationBean<LogFilter>()
        filter.filter = LogFilter(objectMapper)
        filter.order = 0
        return filter
    }
}
