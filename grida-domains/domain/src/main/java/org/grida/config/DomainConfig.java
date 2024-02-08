package org.grida.config;

import org.grida.datetime.DateTimePicker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public DateTimePicker dateTimePicker() {
        return new DateTimePicker();
    }
}
