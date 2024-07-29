package org.grida.support.requestlogger

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean

class LogFilterRegistrar {

    @Bean
    fun logFilter(
        objectMapper: ObjectMapper
    ): FilterRegistrationBean<LogFilter> {
        val filterRegistration = FilterRegistrationBean<LogFilter>()
        filterRegistration.filter = LogFilter(objectMapper)
        filterRegistration.order = 0
        return filterRegistration
    }
}
