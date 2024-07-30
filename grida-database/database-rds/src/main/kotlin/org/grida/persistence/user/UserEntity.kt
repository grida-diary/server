package org.grida.persistence.user

import org.grida.domain.user.Role
import org.grida.domain.user.User
import org.grida.persistence.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
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

    @Enumerated(EnumType.STRING)
    var role: Role,

    var nickname: String
) : BaseEntity()
