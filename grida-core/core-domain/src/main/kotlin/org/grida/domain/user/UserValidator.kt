package org.grida.domain.user

import org.grida.exception.PasswordConfirmNotMatchedException
import org.grida.exception.UnusableUsernameException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserValidator(
    private val userRepository: UserRepository
) {

    fun validatePasswordConfirmMatches(
        password: String,
        passwordConfirm: String
    ) {
        if (password != passwordConfirm) {
            throw PasswordConfirmNotMatchedException()
        }
    }

    @Transactional
    fun validateUsernameAlreadyInUse(username: String) {
        if (userRepository.existsByUsername(username)) {
            throw UnusableUsernameException()
        }
    }
}