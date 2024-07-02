package org.grida.domain.user

interface UserRepository {

    fun save(user: User): Long

    fun findById(id: Long): User

    fun findByUsername(username: String): User

    fun existsByUsername(username: String): Boolean
}
