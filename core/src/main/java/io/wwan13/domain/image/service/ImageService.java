package io.wwan13.domain.image.service;

import io.wwan13.domain.image.entity.Image;
import io.wwan13.domain.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
