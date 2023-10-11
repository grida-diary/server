package io.wwan13.domain.image.entity;

import io.wwan13.common.basetime.BaseTimeEntity;
import io.wwan13.domain.image.entity.wrap.ImageUrl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Embedded
    private ImageUrl url;

    public Image(String url) {
        this.url = new ImageUrl(url);
    }

}
