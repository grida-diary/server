package io.wwan13.domain.diaryimage.entity;

import io.wwan13.common.basetime.BaseTimeEntity;
import io.wwan13.domain.diary.entity.Diary;
import io.wwan13.domain.image.entity.Image;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @Builder
    public DiaryImage(Diary diary, Image image) {
        this.diary = diary;
        this.image = image;
    }
}
