package org.grida.docs.user

import org.grida.docs.ApiDocsTest
import org.grida.presentation.v1.user.UserController
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [UserController::class])
class UserApiDocsTest(
    private val userController: UserController
) : ApiDocsTest(
    userController,
    "user"
)
