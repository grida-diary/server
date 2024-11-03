package org.grida.domain.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun appendNormalUser(
        name: String,
        loginOption: LoginOption
    ): Long {
        val user = User(loginOption = loginOption)
        return userRepository.save(user)
    }

    @Transactional
    fun appendAndReturnNormalUser(
        name: String,
        loginOption: LoginOption
    ): User {
        val user = User(loginOption = loginOption)
        val userid = userRepository.save(user)
        return userRepository.findById(userid)
    }

    fun read(id: Long): User {
        return userRepository.findById(id)
    }

    fun readUserByLoginOptionOrNull(
        loginOption: LoginOption
    ): User? {
        return userRepository.findByLoginOptionOrNull(loginOption)
    }

    @Transactional
    fun updateProfile(
        id: Long,
        profile: UserProfile
    ) {
        userRepository.updateProfileById(id, profile)
    }

    @Transactional
    fun updateTheme(
        id: Long,
        theme: String
    ) {
        userRepository.updateThemeById(id, theme)
    }
}
