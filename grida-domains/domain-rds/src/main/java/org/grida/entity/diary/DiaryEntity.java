package org.grida.entity.diary;

import lombok.*;
import org.grida.domain.diary.DiaryContents;
import org.grida.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "diary")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DiaryEntity extends BaseEntity {

    @Column
    private String userEmail;

    @Column
    private LocalDate targetDate;

    @Column
    private String content;

    @Column
    private Integer imageRefreshChance;

    @Builder
    public DiaryEntity(
            LocalDateTime createdAt,
            LocalDateTime lastModifiedAt,
            String userEmail,
            LocalDate targetDate,
            String content,
            Integer imageRefreshChance
    ) {
        super(createdAt, lastModifiedAt);
        this.userEmail = userEmail;
        this.targetDate = targetDate;
        this.content = content;
        this.imageRefreshChance = imageRefreshChance;
    }

    public long modifyContents(DiaryContents contents, LocalDateTime lastActionAt) {
        content = contents.content();
        targetDate = contents.targetDate();
        lastModifiedAt = lastActionAt;
        return id;
    }

    public void useImageRefreshChange(LocalDateTime lastActionAt) {
        imageRefreshChance -= 1;
        lastModifiedAt = lastActionAt;
    }
}
