package org.grida.persistence.user

import org.grida.domain.user.Role
import org.grida.domain.user.User
import org.grida.persistence.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long = 0,

    var username: String,

    @Column(name = "encrypted_password")
    var password: String,

    var role: Role,

    var nickname: String
) : BaseEntity() {

    fun toUser(): User {
        return User(
            id = id,
            username = username,
            password = password,
            role = role,
            nickname = nickname,
            timestamp = toTimeStamp()
        )
    }

    companion object {
        fun from(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                username = user.username,
                password = user.password,
                role = user.role,
                nickname = user.nickname
            )
        }
    }
}
