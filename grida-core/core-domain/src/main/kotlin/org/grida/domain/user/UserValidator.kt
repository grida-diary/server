package org.grida.domain.user

import org.grida.error.GridaException
import org.grida.error.PasswordConfirmNotMatched
import org.grida.error.UnusableUsername
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
            throw GridaException(PasswordConfirmNotMatched)
        }
    }

    @Transactional(readOnly = true)
    fun validateUsernameAlreadyInUse(username: String) {
        if (userRepository.existsByUsername(username)) {
            throw GridaException(UnusableUsername)
        }
    }
}
