package org.grida.persistence.profileimage

import org.grida.domain.image.Image
import org.grida.domain.image.ImageStatus
import org.grida.domain.profileimage.Appearance
import org.grida.domain.profileimage.Gender
import org.grida.domain.profileimage.ProfileImage
import org.grida.domain.user.User
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

    var additional: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity
) : BaseEntity() {

    fun toProfileImage(): ProfileImage {
        return ProfileImage(
            id = id,
            userId = user.id,
            image = Image(
                url = imageUrl,
                status = status,
                timestamp = toTimeStamp()
            ),
            appearance = Appearance(
                gender = gender,
                age = age,
                hairStyle = hairStyle,
                glasses = glasses,
                bodyShape = bodyShape,
                additional = additional
            )
        )
    }

    companion object {
        fun from(
            profileImage: ProfileImage,
            user: User
        ): ProfileImageEntity {
            return ProfileImageEntity(
                imageUrl = profileImage.image.url,
                status = profileImage.image.status,
                gender = profileImage.appearance.gender,
                age = profileImage.appearance.age,
                hairStyle = profileImage.appearance.hairStyle,
                glasses = profileImage.appearance.glasses,
                bodyShape = profileImage.appearance.bodyShape,
                additional = profileImage.appearance.additional,
                user = UserEntity.from(user)
            )
        }
    }
}
