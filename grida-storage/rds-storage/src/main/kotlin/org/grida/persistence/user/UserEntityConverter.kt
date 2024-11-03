package org.grida.persistence.user

import org.grida.domain.user.LoginOption
import org.grida.domain.user.User
import org.grida.domain.user.UserProfile

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        role = this.role,
        platform = this.loginOption.platform,
        platformIdentifier = this.loginOption.identifier,
        nickname = this.profile.nickname,
        gender = this.profile.gender,
        age = this.profile.age,
        theme = this.theme
    )
}

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        role = this.role,
        timestamp = this.toTimeStamp(),
        loginOption = LoginOption(this.platform, this.platformIdentifier),
        profile = UserProfile(
            nickname = this.nickname,
            gender = this.gender,
            age = this.age,
        ),
        theme = this.theme
    )
}
