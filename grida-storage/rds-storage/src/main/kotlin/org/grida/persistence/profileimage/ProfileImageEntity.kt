package org.grida.persistence.profileimage

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.grida.domain.image.ImageStatus
import org.grida.domain.profileimage.Appearance
import org.grida.domain.profileimage.Gender
import org.grida.persistence.base.BaseEntity
import org.grida.persistence.user.UserEntity

@Entity
@Table(name = "profile_image")
class ProfileImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_image_id")
    var id: Long = 0,

    @Column(length = 511)
    var imageUrl: String,

    @Enumerated(EnumType.STRING)
    @Column(length = 127)
    var status: ImageStatus,

    var gender: Appearance.Gender,

    var age: Int,

    var hairStyle: String,

    var glasses: String,

    var bodyShape: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity,
) : BaseEntity() {

    fun updateStatue(status: ImageStatus) {
        this.status = status
    }
}
