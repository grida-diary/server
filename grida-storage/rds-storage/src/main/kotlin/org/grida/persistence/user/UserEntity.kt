package org.grida.persistence.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.grida.domain.user.LoginPlatform
import org.grida.domain.user.Role
import org.grida.domain.user.UserProfile
import org.grida.persistence.base.BaseEntity

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long = 0,

    @Enumerated(EnumType.STRING)
    var role: Role,

    @Enumerated(EnumType.STRING)
    val platform: LoginPlatform,

    val platformIdentifier: String,

    var nickname: String,

    @Enumerated(EnumType.STRING)
    var gender: UserProfile.Gender,

    var age: Int,

    var theme: String,
) : BaseEntity() {

    fun updateProfile(profile: UserProfile) {
        nickname = profile.nickname
        gender = profile.gender
        age = profile.age
    }

    fun updateTheme(theme: String) {
        this.theme = theme
    }
}
