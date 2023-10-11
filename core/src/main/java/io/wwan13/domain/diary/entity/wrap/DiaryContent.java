package io.wwan13.domain.diary.entity.wrap;

import io.wwan13.domain.diary.exception.ContentTooLongException;
import io.wwan13.domain.diary.exception.ContentTooShortException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryContent {

    private static final int CONTENT_MIN_SIZE = 50;
    private static final int CONTENT_MAX_SIZE = 4095;

    @Column(length = 4095)
    private String content;

    public DiaryContent(String content) {
        validate(content);
        this.content = content;
    }

    private void validate(String content) {
        if (isShortContent(content)) {
            throw new ContentTooShortException();
        } else if (isLongContent(content)) {
            throw new ContentTooLongException();
        }
    }

    private boolean isShortContent(String content) {
        return content.length() < CONTENT_MIN_SIZE;
    }

    private boolean isLongContent(String content) {
        return content.length() > CONTENT_MAX_SIZE;
    }
}
