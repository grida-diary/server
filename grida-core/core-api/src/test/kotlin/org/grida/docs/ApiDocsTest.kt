package org.grida.docs

import io.mockk.junit5.MockKExtension
import io.wwan13.api.document.snippets.DATETIME
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentSummary
import io.wwan13.api.document.snippets.OBJECT
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import io.wwan13.implmockmvc.MockMvcApiDocsTest
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

@ExtendWith(
    value = [
        RestDocumentationExtension::class,
        MockKExtension::class
    ]
)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
abstract class ApiDocsTest(
    private val targetController: Any,
    private val tag: String = DocumentSummary.DEFAULT_DOCUMENT_TAG
) : MockMvcApiDocsTest() {

    @Autowired
    lateinit var restDocumentationContextProvider: RestDocumentationContextProvider

    override fun mockMvc(): MockMvc {
        return MockMvcBuilders
            .standaloneSetup(targetController)
            .setCustomArgumentResolvers(StubUserIdResolver())
            .alwaysDo<StandaloneMockMvcBuilder>(MockMvcResultHandlers.print())
            .alwaysExpect<StandaloneMockMvcBuilder>(MockMvcResultMatchers.status().isOk)
            .apply<StandaloneMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(restDocumentationContextProvider)
            )
            .build()
    }

    override fun tag(): String {
        return tag
    }

    override fun commonResponseField(): List<DocumentField> {
        return listOf(
            "status" isTypeOf STRING whichMeans "응답 상태",
            "timestamp" isTypeOf DATETIME whichMeans "응답 시간",
            "data" isTypeOf OBJECT whichMeans "응답 데이터",
        )
    }
}
