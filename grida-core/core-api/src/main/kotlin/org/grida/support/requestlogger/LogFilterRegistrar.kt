package org.grida.support.requestlogger

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean

class LogFilterRegistrar {

    @Bean
    fun logFilter(): FilterRegistrationBean<LogFilter> {
        val filterRegistration = FilterRegistrationBean<LogFilter>()
        filterRegistration.filter = LogFilter()
        filterRegistration.order = 0
        return filterRegistration
    }
}
