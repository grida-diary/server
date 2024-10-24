package org.deskmood

import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration

@EnableFeignClients(basePackages = ["org.grida"])
@ImportAutoConfiguration(classes = [FeignAutoConfiguration::class])
class FeignClientConfig
