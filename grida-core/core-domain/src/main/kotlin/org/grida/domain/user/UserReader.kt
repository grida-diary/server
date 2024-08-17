package org.grida.domain.user

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserReader(
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun read(id: Long): User {
        return userRepository.findById(id)
    }

    @Transactional(readOnly = true)
    fun read(username: String): User {
        return userRepository.findByUsername(username)
    }
}
