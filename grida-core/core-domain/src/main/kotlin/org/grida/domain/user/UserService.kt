package org.grida.domain.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository
) {

    fun appendNormalUser(
        name: String,
        loginOption: LoginOption
    ): Long {
        val user = User(
            name = name,
            loginOption = loginOption
        )
        return userRepository.save(user)
    }

    fun appendAndReturnNormalUser(
        name: String,
        loginOption: LoginOption
    ): User {
        val user = User(
            name = name,
            loginOption = loginOption
        )
        val userid = userRepository.save(user)
        return userRepository.findById(userid)
    }

    fun read(id: Long): User {
        return userRepository.findById(id)
    }

    fun readUserByLoginOption(
        loginOption: LoginOption
    ): User? {
        return userRepository.findByLoginOption(loginOption)
    }
}
