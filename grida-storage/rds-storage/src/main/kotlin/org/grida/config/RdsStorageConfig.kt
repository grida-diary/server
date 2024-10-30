package org.grida.config

import com.linecorp.kotlinjdsl.render.RenderContext
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EntityScan(basePackages = ["org.grida"])
@EnableJpaRepositories(basePackages = ["org.grida"])
class RdsStorageConfig {

    @Bean
    fun jpqlReaderContext(): RenderContext {
        return JpqlRenderContext()
    }
}
