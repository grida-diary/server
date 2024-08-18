package org.grida.domain.user

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserAppender(
    private val userRepository: UserRepository
) {

    @Transactional
    fun append(user: User): Long {
        return userRepository.save(user)
    }

    @Transactional
    fun appendAndReturnUser(user: User): User {
        val userid = userRepository.save(user)
        return userRepository.findById(userid)
    }
}
