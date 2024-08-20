package org.grida.persistence.profileimage

import org.grida.domain.image.ImageStatus
import org.grida.domain.profileimage.Gender
import org.grida.persistence.base.BaseEntity
import org.grida.persistence.user.UserEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

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

    var gender: Gender,

    var age: Int,

    var hairStyle: String,

    var glasses: String,

    var bodyShape: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity
) : BaseEntity() {

    fun updateStatue(status: ImageStatus) {
        this.status = status
    }
}
