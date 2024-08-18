package org.grida.domain.user

import org.grida.error.AlreadyRegisteredUser
import org.grida.error.GridaException
import org.springframework.stereotype.Component

@Component
class UserValidator(
    private val userRepository: UserRepository
) {

    fun validateAlreadyRegistered(loginOption: LoginOption) {
        if (userRepository.existsByLoginOption(loginOption)) {
            throw GridaException(AlreadyRegisteredUser)
        }
    }
}
