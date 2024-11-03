package org.grida.config

import org.grida.auth.AuthFilter
import org.grida.auth.AuthFilterExceptionHandler
import org.grida.auth.ForbiddenHandler
import org.grida.auth.UnauthorizedHandler
import org.grida.support.UserIdResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
class CoreApiSecurityConfig(
    private val authFilter: AuthFilter,
    private val authFilterExceptionHandler: AuthFilterExceptionHandler,
    private val unauthorizedHandler: UnauthorizedHandler,
    private val forbiddenHandler: ForbiddenHandler,
) : WebMvcConfigurer {

    override fun addArgumentResolvers(
        resolvers: MutableList<HandlerMethodArgumentResolver>,
    ) {
        resolvers.add(UserIdResolver())
    }

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
    ): SecurityFilterChain {
        http
            .cors { }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(authFilter, BasicAuthenticationFilter::class.java)
            .addFilterBefore(authFilterExceptionHandler, AuthFilter::class.java)
            .exceptionHandling {
                it.accessDeniedHandler(forbiddenHandler)
                it.authenticationEntryPoint(unauthorizedHandler)
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/v1/**").permitAll()
                    .anyRequest().permitAll()
            }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            allowedOrigins = listOf("http://localhost:3000", "https://grida.today")
            allowedMethods = listOf("GET", "POST", "PUT", "PATCH", "DELETE")
            allowedHeaders = listOf("Authorization", "Cache-Control", "Content-Type")
            allowCredentials = true
            applyPermitDefaultValues()
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
