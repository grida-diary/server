package io.wwan13.domain.image.service;

import io.wwan13.domain.image.entity.Image;
import io.wwan13.domain.image.exception.ImageNotFoundException;
import io.wwan13.domain.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageQueryService {

    private final ImageRepository imageRepository;

    public Image findById(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ImageNotFoundException());
    }
}
