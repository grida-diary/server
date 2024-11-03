package org.grida.persistence.profileimage

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.grida.persistence.base.BaseEntity
import org.grida.persistence.image.ImageEntity
import org.grida.persistence.user.UserEntity

@Entity
@Table(name = "profile_image")
class ProfileImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_image_id")
    var id: Long = 0,

    @Column(length = 511)
    var appearance: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    var image: ImageEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity,
) : BaseEntity()
