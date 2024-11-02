package org.grida.domain.user

interface UserRepository {

    fun save(user: User): Long

    fun findById(id: Long): User

    fun findByLoginOption(loginOption: LoginOption): User

    fun findByLoginOptionOrNull(loginOption: LoginOption): User?

    fun existsByLoginOption(loginOption: LoginOption): Boolean

    fun updateProfileById(id: Long, userProfile: UserProfile)

    fun updateThemeById(id: Long, theme: String)
}
