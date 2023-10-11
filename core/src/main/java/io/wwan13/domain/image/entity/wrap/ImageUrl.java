package io.wwan13.domain.image.entity.wrap;

import io.wwan13.domain.image.exception.NotValidUrlPrefixException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ImageUrl {

    private final static String OPEN_AI_IMAGE_PREFIX = "https://oaidalleapiprodscus.blob.core.windows.net/";

    @Column(length = 511)
    private String url;

    public ImageUrl(String url) {
        validate(url);
        this.url = url;
    }

    private void validate(String url) {
        if (!isRightUrlPrefix(url)) {
            throw new NotValidUrlPrefixException();
        }
    }

    private boolean isRightUrlPrefix(String url) {
        return url.startsWith(OPEN_AI_IMAGE_PREFIX);
    }

}
