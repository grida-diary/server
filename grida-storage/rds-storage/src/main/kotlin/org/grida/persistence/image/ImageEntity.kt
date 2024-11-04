package org.grida.persistence.image

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.grida.domain.image.ImageStatus
import org.grida.persistence.base.BaseEntity

@Entity
@Table(name = "image")
class ImageEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    var id: Long = 0,

    @Column(length = 511)
    var url: String,

    @Column(length = 127)
    @Enumerated(EnumType.STRING)
    var status: ImageStatus = ImageStatus.DEACTIVATE,
) : BaseEntity() {

    fun updateStatue(status: ImageStatus) {
        this.status = status
    }
}
