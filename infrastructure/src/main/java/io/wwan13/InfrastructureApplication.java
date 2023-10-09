package io.wwan13;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "io.wwan13")
@EnableFeignClients
public class InfrastructureApplication {
}
