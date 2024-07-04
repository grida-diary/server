package org.grida.docs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(
    value = [
        RestDocumentationExtension::class,
        MockKExtension::class
    ]
)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
abstract class RestDocsTest {

    lateinit var mockMvc: MockMvc
    lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp(
        webApplicationContext: WebApplicationContext,
        restDocumentationContextProvider: RestDocumentationContextProvider
    ) {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder>(
                documentationConfiguration(restDocumentationContextProvider)
            )
            .build()

        objectMapper = jacksonObjectMapper()
    }
}
