package org.grida.persistence.diary

import org.grida.domain.diary.Diary
import org.grida.domain.diary.DiaryScope
import org.grida.domain.user.User
import org.grida.persistence.base.BaseEntity
import org.grida.persistence.user.UserEntity
import java.time.LocalDate
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
    var user: UserEntity
) : BaseEntity() {

    fun toDiary(): Diary {
        return Diary(
            id = id,
            timestamp = this.toTimeStamp(),
            targetDate = targetDate,
            content = content,
            scope = scope,
            userId = user.id
        )
    }

    fun updateContent(content: String) {
        this.content = content
    }

    fun updateScope(scope: DiaryScope) {
        this.scope = scope
    }

    companion object {
        fun from(
            diary: Diary,
            user: User
        ): DiaryEntity {
            return DiaryEntity(
                id = diary.id,
                targetDate = diary.targetDate,
                content = diary.content,
                scope = diary.scope,
                user = UserEntity.from(user)
            )
        }
    }
}