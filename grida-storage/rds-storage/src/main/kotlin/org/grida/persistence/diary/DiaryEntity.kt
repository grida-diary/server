package org.grida.persistence.diary

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
import org.grida.domain.diary.DiaryScope
import org.grida.persistence.base.BaseEntity
import org.grida.persistence.user.UserEntity
import java.time.LocalDate

@Entity
@Table(name = "diary")
class DiaryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    var id: Long = 0,

    var targetDate: LocalDate,

    @Column(length = 4095)
    var content: String,

    @Enumerated(EnumType.STRING)
    var scope: DiaryScope,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity,
) : BaseEntity() {

    fun updateContent(content: String) {
        this.content = content
    }

    fun updateScope(scope: DiaryScope) {
        this.scope = scope
    }
}
