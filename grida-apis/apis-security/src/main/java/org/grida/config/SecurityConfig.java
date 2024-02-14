package org.grida.config;

import io.jsonwebtoken.security.Keys;
import org.grida.datetime.DateTimePicker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;

import java.security.Key;

@Configuration
@ConfigurationPropertiesScan(basePackages = "org.grida")
public class SecurityConfig {

    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }

    @Bean
    public Key key(JwtProperties jwtProperties) {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @ConditionalOnMissingBean
    public DateTimePicker dateTimePicker() {
        return new DateTimePicker();
    }
}
