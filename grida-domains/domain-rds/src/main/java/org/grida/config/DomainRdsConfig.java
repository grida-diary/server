package org.grida.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class DomainRdsConfig {

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate();
    }
}
