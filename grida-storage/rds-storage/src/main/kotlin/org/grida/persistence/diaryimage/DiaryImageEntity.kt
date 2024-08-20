package org.grida.persistence.diaryimage

import org.grida.domain.image.ImageStatus
import org.grida.persistence.base.BaseEntity
import org.grida.persistence.diary.DiaryEntity
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
@Table(name = "diary_image")
class DiaryImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_image_id")
    var id: Long = 0,

    @Column(length = 511)
    var imageUrl: String,

    @Enumerated(EnumType.STRING)
    @Column(length = 127)
    var status: ImageStatus,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    var diary: DiaryEntity,
) : BaseEntity() {

    fun updateStatue(status: ImageStatus) {
        this.status = status
    }
}
