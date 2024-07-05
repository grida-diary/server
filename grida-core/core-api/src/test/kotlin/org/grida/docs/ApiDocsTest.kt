package org.grida.docs

import io.mockk.junit5.MockKExtension
import io.wwan13.api.document.snippets.DATETIME
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.OBJECT
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import io.wwan13.implmockmvc.MockMvcApiDocsTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(
    value = [
        RestDocumentationExtension::class,
        MockKExtension::class
    ]
)
abstract class ApiDocsTest : MockMvcApiDocsTest() {

    protected lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp(
        webApplicationContext: WebApplicationContext,
        restDocumentationContextProvider: RestDocumentationContextProvider
    ) {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .apply<DefaultMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(restDocumentationContextProvider)
            )
            .build()
    }

    override fun mockMvc(): MockMvc {
        return mockMvc
    }

    override fun commonResponseField(): List<DocumentField> {
        return listOf(
            "status" isTypeOf STRING whichMeans "응답 상태",
            "timestamp" isTypeOf DATETIME whichMeans "응답 시간",
            "data" isTypeOf OBJECT whichMeans "응답 데이터",
        )
    }
}
