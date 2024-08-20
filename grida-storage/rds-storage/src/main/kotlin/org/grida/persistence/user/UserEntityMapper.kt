package org.grida.persistence.user

import org.grida.domain.user.LoginOption
import org.grida.domain.user.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        role = this.role,
        name = this.name,
        platform = this.loginOption.platform,
        platformIdentifier = this.loginOption.identifier,
    )
}

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        role = this.role,
        name = this.name,
        timestamp = this.toTimeStamp(),
        loginOption = LoginOption(this.platform, this.platformIdentifier)
    )
}
