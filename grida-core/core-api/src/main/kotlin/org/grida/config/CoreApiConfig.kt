package org.grida.config

import org.grida.support.requestlogger.EnableLogRequest
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan
@EnableLogRequest
class CoreApiConfig
