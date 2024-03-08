package org.grida.entity.diaryimage;

import lombok.*;
import org.grida.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "diary_image", indexes = {@Index(name = "idx_diary_id", columnList = "userEmail")})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DiaryImageEntity extends BaseEntity {

    @Column
    private Long diaryId;

    @Column
    private String imagePath;

    @Column
    private Boolean isActivate;

    @Builder
    public DiaryImageEntity(
            LocalDateTime createdAt,
            LocalDateTime lastModifiedAt,
            Long diaryId,
            String imagePath,
            Boolean isActivate
    ) {
        super(createdAt, lastModifiedAt);
        this.diaryId = diaryId;
        this.imagePath = imagePath;
        this.isActivate = isActivate;
    }
}
