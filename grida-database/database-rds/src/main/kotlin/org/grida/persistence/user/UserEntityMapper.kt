package org.grida.persistence.user

import org.grida.domain.user.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        username = this.username,
        password = this.password,
        role = this.role,
        nickname = this.nickname
    )
}

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        username = this.username,
        password = this.password,
        role = this.role,
        nickname = this.nickname,
        timestamp = this.toTimeStamp()
    )
}