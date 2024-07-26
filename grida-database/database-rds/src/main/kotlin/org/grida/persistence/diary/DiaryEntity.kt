package org.grida.persistence.diary

import org.grida.domain.diary.Diary
import org.grida.domain.diary.DiaryScope
import org.grida.persistence.base.BaseEntity
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "diary")
class DiaryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    var id: Long = 0,

    var targetDate: LocalDate,

    var content: String,

    @Column(length = 4095)
    var scope: DiaryScope
) : BaseEntity() {

    fun toDiary(): Diary {
        return Diary(
            id = id,
            timestamp = this.toTimeStamp(),
            targetDate = targetDate,
            content = content,
            scope = scope
        )
    }

    companion object {
        fun from(diary: Diary): DiaryEntity {
            return DiaryEntity(
                id = diary.id,
                targetDate = diary.targetDate,
                content = diary.content,
                scope = diary.scope,
            )
        }
    }
}