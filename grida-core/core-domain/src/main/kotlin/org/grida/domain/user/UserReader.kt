package org.grida.domain.user

import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserReader(
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun read(id: Long): User {
        return userRepository.findById(id)
            ?: throw GridaException(NoSuchData(User::class, id))
    }

    @Transactional(readOnly = true)
    fun readByLoginOptionOrNull(loginOption: LoginOption): User? {
        return userRepository.findByLoginOption(loginOption)
    }
}
